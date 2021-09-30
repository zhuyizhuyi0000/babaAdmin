package cn.stylefeng.guns.modular.resource.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.stylefeng.guns.core.exception.BusinessException;
import cn.stylefeng.guns.modular.resource.entity.TagDoc;
import cn.stylefeng.guns.modular.resource.exception.ResourceExceptionEnum;
import cn.stylefeng.guns.modular.resource.mapper.TagDocMapper;
import cn.stylefeng.guns.modular.resource.pojo.TagDocRequest;
import cn.stylefeng.guns.modular.resource.service.TagDocService;
import cn.stylefeng.roses.kernel.db.api.factory.PageFactory;
import cn.stylefeng.roses.kernel.db.api.factory.PageResultFactory;
import cn.stylefeng.roses.kernel.db.api.pojo.page.PageResult;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/** * 文档标签管理业务实现层   */
@Service
public class TagDocServiceImpl extends ServiceImpl<TagDocMapper, TagDoc> implements TagDocService {

    @Override
    public void add(TagDocRequest tagdocRequest){
        TagDoc tagdoc = new TagDoc();
        BeanUtil.copyProperties(tagdocRequest,tagdoc);
        this.save(tagdoc);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void del(TagDocRequest tagdocRequest){
        TagDoc tagdoc = this.queryTagDoc(tagdocRequest);
        this.removeById(tagdoc.getId());
    }

    @Override
    public void edit(TagDocRequest tagdocRequest){
        TagDoc tagdoc=this.queryTagDoc(tagdocRequest);
        BeanUtil.copyProperties(tagdocRequest,tagdoc);
        this.updateById(tagdoc);
    }

    @Override
    public TagDoc detail(TagDocRequest tagdocRequest){
        return this.queryTagDoc(tagdocRequest);
    }

    @Override
    public PageResult<TagDoc> findPage(TagDocRequest tagdocRequest){
        LambdaQueryWrapper<TagDoc> wrapper = createWrapper(tagdocRequest);
        Page<TagDoc> sysRolePage = this.page(PageFactory.defaultPage(),wrapper);
        return PageResultFactory.createPageResult(sysRolePage);
    }

    @Override
    public List<TagDoc> findList(TagDocRequest tagdocRequest){
        LambdaQueryWrapper<TagDoc> wrapper = this.createWrapper(tagdocRequest);
        return this.list(wrapper);
    }

    /** *获取视频标签信息   */
    private TagDoc queryTagDoc(TagDocRequest tagdocRequest){
        TagDoc tagdoc = this.getById(tagdocRequest.getId());
        if(ObjectUtil.isEmpty(tagdoc)){
            throw new BusinessException(ResourceExceptionEnum.VIDEO_NOT_EXISTED);
        }
        return tagdoc;
    }

    /** * 创建查询wrapper  */
    private LambdaQueryWrapper<TagDoc> createWrapper(TagDocRequest tagdocRequest){
        LambdaQueryWrapper<TagDoc> queryWrapper = new LambdaQueryWrapper<>();

        String tagdocName = tagdocRequest.getName();
        queryWrapper.like(ObjectUtil.isNotEmpty(tagdocName),TagDoc::getName,tagdocName);

        /** * 根据时间倒序排列  */
        queryWrapper.orderByDesc(TagDoc::getCreateTime);

        return queryWrapper;
    }
}
