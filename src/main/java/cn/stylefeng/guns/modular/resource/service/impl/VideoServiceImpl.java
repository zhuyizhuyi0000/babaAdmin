package cn.stylefeng.guns.modular.resource.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.stylefeng.guns.core.exception.BusinessException;
import cn.stylefeng.guns.modular.resource.controller.VideoController;
import cn.stylefeng.guns.modular.resource.entity.Video;
import cn.stylefeng.guns.modular.resource.exception.ResourceExceptionEnum;
import cn.stylefeng.guns.modular.resource.mapper.VideoMapper;
import cn.stylefeng.guns.modular.resource.pojo.VideoRequest;
import cn.stylefeng.guns.modular.resource.service.VideoService;
import cn.stylefeng.roses.kernel.db.api.factory.PageFactory;
import cn.stylefeng.roses.kernel.db.api.factory.PageResultFactory;
import cn.stylefeng.roses.kernel.db.api.pojo.page.PageResult;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/** * 视频管理业务实现层   */
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements VideoService {

    @Override
    public  void add(VideoRequest videoRequest){
        Video video = new Video();
        BeanUtil.copyProperties(videoRequest,video);
        this.save(video);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void del(VideoRequest videoRequest){
        Video video = this.queryVideo(videoRequest);
        this.removeById(video.getVidId());
    }

    @Override
    public void edit(VideoRequest videoRequest){
        Video video=this.queryVideo(videoRequest);
        BeanUtil.copyProperties(videoRequest,video);
        this.updateById(video);
    }

    @Override
    public Video detail(VideoRequest videoRequest){
        return this.queryVideo(videoRequest);
    }

    @Override
    public PageResult<Video> findPage(VideoRequest videoRequest){
        LambdaQueryWrapper<Video> wrapper = createWrapper(videoRequest);
        Page<Video> sysRolePage = this.page(PageFactory.defaultPage(),wrapper);
        return PageResultFactory.createPageResult(sysRolePage);
    }

    @Override
    public List<Video> findList(VideoRequest videoRequest){
        LambdaQueryWrapper<Video> wrapper = this.createWrapper(videoRequest);
        return this.list(wrapper);
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
