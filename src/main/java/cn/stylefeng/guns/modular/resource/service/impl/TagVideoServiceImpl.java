package cn.stylefeng.guns.modular.resource.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.stylefeng.guns.core.exception.BusinessException;
import cn.stylefeng.guns.modular.resource.entity.TagVideo;
import cn.stylefeng.guns.modular.resource.entity.Video;
import cn.stylefeng.guns.modular.resource.exception.ResourceExceptionEnum;
import cn.stylefeng.guns.modular.resource.mapper.TagVideoMapper;
import cn.stylefeng.guns.modular.resource.pojo.TagVideoRequest;
import cn.stylefeng.guns.modular.resource.service.TagLinkVideoService;
import cn.stylefeng.guns.modular.resource.service.TagVideoService;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** * 视频标签管理业务实现层   */
@Slf4j
@Service
public class TagVideoServiceImpl extends ServiceImpl<TagVideoMapper, TagVideo> implements TagVideoService {

    @Resource
    private TagLinkVideoService tagLinkVideoService;

    @Override
    public void add(TagVideoRequest tagvideoRequest){
        TagVideo tagvideo = new TagVideo();
        BeanUtil.copyProperties(tagvideoRequest,tagvideo);
        this.save(tagvideo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long del(TagVideoRequest tagvideoRequest){
        TagVideo tagvideo = this.queryTagVideo(tagvideoRequest);
        this.removeById(tagvideo.getId());

        tagLinkVideoService.del(tagvideo.getId(),2); //1 resource；2tag
        return tagvideo.getId();
    }

    @Override
    public void edit(TagVideoRequest tagvideoRequest){
        TagVideo tagvideo=this.queryTagVideo(tagvideoRequest);
        BeanUtil.copyProperties(tagvideoRequest,tagvideo);
        this.updateById(tagvideo);
    }

    @Override
    public TagVideo detail(TagVideoRequest tagvideoRequest){
        return this.queryTagVideo(tagvideoRequest);
    }

    @Override
    public String getName(Long id){
        TagVideo tagvideo = this.getById(id);
        if(ObjectUtil.isEmpty(tagvideo)){
            throw new BusinessException(ResourceExceptionEnum.VIDEO_NOT_EXISTED);
        }
        String name = tagvideo.getName();
        return name;
    }

    //一次性把所有sql in的都查出来，避免查多次了
    @Override
    public List<TagVideo> findAllTagNameList(List<Long> tagIdList){
        LambdaQueryWrapper<TagVideo> wrapper = this.createAllWrapper(tagIdList);
//        List<TagVideo>  tagList1 = this.list(wrapper);
//        log.info("查出tagVideo集合的list "+tagList1.toString());
        return this.list(wrapper);
    }

    /** * 创建查询wrapper 拿list进去  */
    private LambdaQueryWrapper<TagVideo> createAllWrapper(List<Long> list){
        LambdaQueryWrapper<TagVideo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.in(TagVideo::getId, list);

//        String tagvideoName ="";
//        queryWrapper.like(ObjectUtil.isNotEmpty(tagvideoName),TagVideo::getName,tagvideoName);
        return queryWrapper;
    }

    @Override
    public PageResult<TagVideo> findPage(TagVideoRequest tagvideoRequest){
        LambdaQueryWrapper<TagVideo> wrapper = createWrapper(tagvideoRequest);
        Page<TagVideo> sysRolePage = this.page(PageFactory.defaultPage(),wrapper);
        return PageResultFactory.createPageResult(sysRolePage);
    }

    @Override
    public List<TagVideo> findList(TagVideoRequest tagvideoRequest){
        LambdaQueryWrapper<TagVideo> wrapper = this.createWrapper(tagvideoRequest);
        return this.list(wrapper);
    }

    /** *获取视频标签信息   */
    private TagVideo queryTagVideo(TagVideoRequest tagvideoRequest){
        TagVideo tagvideo = this.getById(tagvideoRequest.getId());
        if(ObjectUtil.isEmpty(tagvideo)){
            throw new BusinessException(ResourceExceptionEnum.VIDEO_NOT_EXISTED);
        }
        return tagvideo;
    }

    /** * 创建查询wrapper  */
    private LambdaQueryWrapper<TagVideo> createWrapper(TagVideoRequest tagvideoRequest){
        LambdaQueryWrapper<TagVideo> queryWrapper = new LambdaQueryWrapper<>();

        String tagvideoName = tagvideoRequest.getName();
        queryWrapper.like(ObjectUtil.isNotEmpty(tagvideoName),TagVideo::getName,tagvideoName);

        /** * 根据时间倒序排列  */
        queryWrapper.orderByDesc(TagVideo::getCreateTime);

        return queryWrapper;
    }
}
