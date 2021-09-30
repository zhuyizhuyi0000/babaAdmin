package cn.stylefeng.guns.modular.resource.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.stylefeng.guns.core.exception.BusinessException;
import cn.stylefeng.guns.modular.resource.entity.TagLinkVideo;
import cn.stylefeng.guns.modular.resource.exception.ResourceExceptionEnum;
import cn.stylefeng.guns.modular.resource.mapper.TagLinkVideoMapper;
import cn.stylefeng.guns.modular.resource.pojo.VideoRequest;
import cn.stylefeng.guns.modular.resource.service.TagLinkVideoService;
import cn.stylefeng.roses.kernel.db.api.factory.PageFactory;
import cn.stylefeng.roses.kernel.db.api.factory.PageResultFactory;
import cn.stylefeng.roses.kernel.db.api.pojo.page.PageResult;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/** * 视频管理业务实现层   */
@Slf4j
@Service
public class TagLinkVideoServiceImpl extends ServiceImpl<TagLinkVideoMapper, TagLinkVideo> implements TagLinkVideoService {

    @Override
    public void add(VideoRequest videoRequest){
        TagLinkVideo tagLinkVideo = new TagLinkVideo();
//        BeanUtil.copyProperties(videoRequest,tagLinkVideo);
//        this.save(tagLinkVideo);
    }

    //批量保存
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void add(List<Long> list1,Long id){
        List<TagLinkVideo> tagLinkVideoList = new ArrayList<>();
        for(Long tagId:list1){
            TagLinkVideo newTagLinkVideo = new TagLinkVideo();
            newTagLinkVideo.setResourceId(id);
            newTagLinkVideo.setTagId(tagId);
            tagLinkVideoList.add(newTagLinkVideo);
        }
        log.info(tagLinkVideoList.toString());
        this.saveBatch(tagLinkVideoList);

    }

    //使用map删除   主键id集合list可以用removeByIds
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void del(Long id,int num){
        HashMap<String,Object> map = new HashMap<>();
        if (num==1){
            map.put("resource_id",id);
            this.removeByMap(map);
        }else if(num==2){
            map.put("tag_id",id);
            this.removeByMap(map);
        }
    }

    @Override
    public List<TagLinkVideo> findList(Long id,int num){
        LambdaQueryWrapper<TagLinkVideo> wrapper = this.createWrapper(id,num);
        return this.list(wrapper);
    }

    //返回只有tagid的list    把对象的list取出只有tagid  1==用resourceid去getTagId
    @Override
    public List<Long> findTagList(Long id,int num){
        LambdaQueryWrapper<TagLinkVideo> wrapper = this.createWrapper(id,num);
        List<TagLinkVideo> tagLinkVideoList = this.list(wrapper);
        List<Long> videoTagList = new ArrayList<>();
        if(tagLinkVideoList.size()==0){return videoTagList;}
        for(int i=0;i<tagLinkVideoList.size();i++){
            if(num==1){
                videoTagList.add(tagLinkVideoList.get(i).getTagId());
            }else {
                videoTagList.add(tagLinkVideoList.get(i).getResourceId());
            }

        }
        return videoTagList;
    }

    //一次性把所有sql in的都查出来，避免查多次了
    @Override
    public List<TagLinkVideo> findAllTagList(List<Long> list,int num){
        LambdaQueryWrapper<TagLinkVideo> wrapper = this.createAllWrapper(list,num);
        return this.list(wrapper);
    }

    /** * 创建查询wrapper  */
    private LambdaQueryWrapper<TagLinkVideo> createWrapper(Long id,int num){
        LambdaQueryWrapper<TagLinkVideo> queryWrapper = new LambdaQueryWrapper<>();
        if(num==1){
            queryWrapper.eq(TagLinkVideo::getResourceId, id);
        }else{
            queryWrapper.eq(TagLinkVideo::getTagId, id);
        }
//        queryWrapper.like(ObjectUtil.isNotEmpty(videoName),Video::getFileName,videoName);

        /** * 根据创建时间倒序排序  */
        queryWrapper.orderByDesc(TagLinkVideo::getCreateTime);

        return queryWrapper;
    }

    /** * 创建查询wrapper 拿list进去  */
    private LambdaQueryWrapper<TagLinkVideo> createAllWrapper(List<Long> list,int num){
        LambdaQueryWrapper<TagLinkVideo> queryWrapper = new LambdaQueryWrapper<>();
        if(num==1){
            queryWrapper.in(TagLinkVideo::getResourceId, list);
        }else{
            queryWrapper.in(TagLinkVideo::getTagId, list);
        }
//        queryWrapper.like(ObjectUtil.isNotEmpty(videoName),Video::getFileName,videoName);

        return queryWrapper;
    }
}
