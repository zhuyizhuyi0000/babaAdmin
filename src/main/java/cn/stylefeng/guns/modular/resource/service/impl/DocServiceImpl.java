package cn.stylefeng.guns.modular.resource.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.stylefeng.guns.core.exception.BusinessException;
import cn.stylefeng.guns.modular.resource.entity.Doc;
import cn.stylefeng.guns.modular.resource.exception.ResourceExceptionEnum;
import cn.stylefeng.guns.modular.resource.mapper.DocMapper;
import cn.stylefeng.guns.modular.resource.pojo.DocRequest;
import cn.stylefeng.guns.modular.resource.service.DocService;
import cn.stylefeng.roses.kernel.db.api.factory.PageFactory;
import cn.stylefeng.roses.kernel.db.api.factory.PageResultFactory;
import cn.stylefeng.roses.kernel.db.api.pojo.page.PageResult;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/** * 文档管理业务实现层   */
@Service
public class DocServiceImpl extends ServiceImpl<DocMapper, Doc> implements DocService {

    @Override
    public  void add(DocRequest docRequest){
        Doc doc = new Doc();
        BeanUtil.copyProperties(docRequest,doc);
        this.save(doc);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void del(DocRequest docRequest){
        Doc doc = this.queryDoc(docRequest);
        this.removeById(doc.getDocId());
    }

    @Override
    public void edit(DocRequest docRequest){
        Doc doc=this.queryDoc(docRequest);
        BeanUtil.copyProperties(docRequest,doc);
        this.updateById(doc);
    }

    @Override
    public Doc detail(DocRequest docRequest){
        return this.queryDoc(docRequest);
    }

    @Override
    public PageResult<Doc> findPage(DocRequest docRequest){
        LambdaQueryWrapper<Doc> wrapper = createWrapper(docRequest);
        Page<Doc> sysRolePage = this.page(PageFactory.defaultPage(),wrapper);
        return PageResultFactory.createPageResult(sysRolePage);
    }

    @Override
    public List<Doc> findList(DocRequest docRequest){
        LambdaQueryWrapper<Doc> wrapper = this.createWrapper(docRequest);
        return this.list(wrapper);
    }

    /** *获取文档信息   */
    private Doc queryDoc(DocRequest docRequest){
        Doc doc = this.getById(docRequest.getDocId());
        if(ObjectUtil.isEmpty(doc)){
            throw new BusinessException(ResourceExceptionEnum.VIDEO_NOT_EXISTED);
        }
        return doc;
    }

    /** * 创建查询wrapper  */
    private LambdaQueryWrapper<Doc> createWrapper(DocRequest docRequest){
        LambdaQueryWrapper<Doc> queryWrapper = new LambdaQueryWrapper<>();

        String docName = docRequest.getFileName();
        queryWrapper.like(ObjectUtil.isNotEmpty(docName),Doc::getFileName,docName);

        /** * 根据时间倒序排列  */
        queryWrapper.orderByDesc(Doc::getCreateTime);

        return queryWrapper;
    }
}
