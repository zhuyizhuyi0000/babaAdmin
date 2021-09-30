package cn.stylefeng.guns.modular.resource.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.stylefeng.guns.core.exception.BusinessException;
import cn.stylefeng.guns.modular.resource.controller.VideoController;
import cn.stylefeng.guns.modular.resource.entity.TagLinkVideo;
import cn.stylefeng.guns.modular.resource.entity.Video;
import cn.stylefeng.guns.modular.resource.exception.ResourceExceptionEnum;
import cn.stylefeng.guns.modular.resource.mapper.VideoMapper;
import cn.stylefeng.guns.modular.resource.pojo.VideoRequest;
import cn.stylefeng.guns.modular.resource.service.TagLinkVideoService;
import cn.stylefeng.guns.modular.resource.service.VideoService;
import cn.stylefeng.roses.kernel.db.api.factory.PageFactory;
import cn.stylefeng.roses.kernel.db.api.factory.PageResultFactory;
import cn.stylefeng.roses.kernel.db.api.pojo.page.PageResult;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/** * 视频管理业务实现层   */
@Slf4j
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements VideoService {

    @Resource
    private TagLinkVideoService tagLinkVideoService;

    @Override
    public Long add(VideoRequest videoRequest){
        Video video = new Video();
        BeanUtil.copyProperties(videoRequest,video);
        this.save(video);
        //如果绑定tag字段不是空的话
//        log.info(videoRequest.toString());
//        log.info(video.toString());
        if (videoRequest.getVideoTag()!=null && !videoRequest.getVideoTag().isEmpty()){
//            log.info("66:"+videoRequest.getVideoTag().toString());
            tagLinkVideoService.add(videoRequest.getVideoTag(),video.getVidId());
        }
        return video.getVidId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long del(VideoRequest videoRequest){
        Video video = this.queryVideo(videoRequest);
        this.removeById(video.getVidId());
        //去删tag关联表
        tagLinkVideoService.del(video.getVidId(),1); //1 resource；2tag
        return video.getVidId();
    }

    @Override
    public Long edit(VideoRequest videoRequest){
        Video video=this.queryVideo(videoRequest);
        BeanUtil.copyProperties(videoRequest,video);
        this.updateById(video);
        //先清空这个关联表 然后再重新进行绑定
        tagLinkVideoService.del(video.getVidId(),1); //1 resource；2tag
        if (videoRequest.getVideoTag()!=null && !videoRequest.getVideoTag().isEmpty()){
            tagLinkVideoService.add(videoRequest.getVideoTag(),video.getVidId());
        }
        return video.getVidId();
    }

    @Override
    public Video detail(VideoRequest videoRequest){
        Video video=this.queryVideo(videoRequest);
        //把单个的对应的taglist取出来 加进去
        List<Long> videoTagList = tagLinkVideoService.findTagList(videoRequest.getVidId(),1);
        video.setVideoTag(videoTagList);
        return video;
    }

    @Override
    public PageResult<Video> findPage(VideoRequest videoRequest){
        //拿到了Video的集合列表
//        log.info(videoRequest.toString());
//        LambdaQueryWrapper<Video> wrapper = this.createWrapper(videoRequest);
//        Page<Video> sysRolePage = this.page(PageFactory.defaultPage(),wrapper);
        //和下面一样

        List<Video> videoList = findList(videoRequest);
        Page<Video> sysRolePage1 = PageFactory.defaultPage();
        int current = (int)sysRolePage1.getCurrent();
        int size = (int)sysRolePage1.getSize();
        int count =videoList.size();
        List<Video> pageList = new ArrayList<>();
        //计算当前页第一条数据的下标
        int currId = current>1 ? (current-1) * size:0;
        for(int i=0;i<size && i<count - currId;i++){
            pageList.add(videoList.get(currId+i));
        }
//        sysRolePage1.setSize(size);
//        sysRolePage1.setCurrent(current);
        sysRolePage1.setTotal(count);
        //计算分页总页数
        sysRolePage1.setPages(count % size==0 ? count/size:count/size+1);
        sysRolePage1.setRecords(pageList);
        return PageResultFactory.createPageResult(sysRolePage1);
    }

    @Override
    public List<Video> findList(VideoRequest videoRequest){
        //拿到了Video的集合列表
        LambdaQueryWrapper<Video> wrapper = this.createWrapper(videoRequest);
        //所有video的集合 allVideoList
        List<Video> allVideoList = this.list(wrapper);
        if(allVideoList.size()!=0){
            //把allVideoList转换成resourceID vidId→ vidList
            List<Long> vidList = new ArrayList<>();
            for (int i=0;i<allVideoList.size();i++){
                vidList.add(allVideoList.get(i).getVidId());
            }
            //拿到TagLinkVideo的一个集合，包含了所有符合条件的
            List<TagLinkVideo> sumlist1 = tagLinkVideoService.findAllTagList(vidList,1);
            //再给他塞放进每个video里去
            for (int j=0;j<allVideoList.size();j++){
                //先要再循环一下总集合sumlist1，把每个vid对应的tag弄成一个list onetaglist
                List<Long> oneTagList = new ArrayList<>();
                //循环TagLinkVideo的集合sumlist1，取出resourceid相等的
                for(int k=0;k<sumlist1.size();k++){
                    if(allVideoList.get(j).getVidId() == sumlist1.get(k).getResourceId()){
                        oneTagList.add(sumlist1.get(k).getTagId());
                    }
                }
                allVideoList.get(j).setVideoTag(oneTagList);
            }
        }
        return allVideoList;
    }


    /** *获取视频信息   */
    private Video queryVideo(VideoRequest videoRequest){
        Video video = this.getById(videoRequest.getVidId());
        if(ObjectUtil.isEmpty(video)){
            throw new BusinessException(ResourceExceptionEnum.VIDEO_NOT_EXISTED);
        }
        return video;
    }

    /** * 创建查询wrapper  */
    private LambdaQueryWrapper<Video> createWrapper(VideoRequest videoRequest){
        LambdaQueryWrapper<Video> queryWrapper = new LambdaQueryWrapper<>();

        String videoName = videoRequest.getFileName();
        queryWrapper.like(ObjectUtil.isNotEmpty(videoName),Video::getFileName,videoName);

        /** * 根据时间倒序排列  */
        queryWrapper.orderByDesc(Video::getCreateTime);

        return queryWrapper;
    }
}
