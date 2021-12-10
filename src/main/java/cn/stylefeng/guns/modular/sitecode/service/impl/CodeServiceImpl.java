package cn.stylefeng.guns.modular.sitecode.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.stylefeng.guns.core.exception.BusinessException;
import cn.stylefeng.guns.modular.resource.entity.TagLinkDoc;
import cn.stylefeng.guns.modular.sitecode.entity.Code;
import cn.stylefeng.guns.modular.sitecode.exception.ResourceExceptionEnum;
import cn.stylefeng.guns.modular.sitecode.mapper.CodeMapper;
import cn.stylefeng.guns.modular.sitecode.pojo.CodeRequest;
import cn.stylefeng.guns.modular.sitecode.service.CodeService;
import cn.stylefeng.roses.kernel.db.api.factory.PageFactory;
import cn.stylefeng.roses.kernel.db.api.factory.PageResultFactory;
import cn.stylefeng.roses.kernel.db.api.pojo.page.PageResult;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** * code管理业务实现层   */
@Slf4j
@Service
public class CodeServiceImpl extends ServiceImpl<CodeMapper, Code> implements CodeService {

    @Override
    public Long add(CodeRequest codeRequest){
        Code code = new Code();
        BeanUtil.copyProperties(codeRequest,code);
        this.save(code);
        return code.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long del(CodeRequest codeRequest){
        Code code = this.queryCode(codeRequest);
        this.removeById(code.getId());
        return code.getId();
    }

    @Override
    public Long edit(CodeRequest codeRequest){
        Code code=this.queryCode(codeRequest);
        BeanUtil.copyProperties(codeRequest,code);
        this.updateById(code);
        return code.getId();
    }

    @Override
    public Code detail(CodeRequest codeRequest){
        Code code=this.queryCode(codeRequest);
        return code;
    }

    @Override
    public PageResult<Code> findPage(CodeRequest codeRequest){
        LambdaQueryWrapper<Code> wrapper = createWrapper(codeRequest);
        Page<Code> sysRolePage = this.page(PageFactory.defaultPage(),wrapper);
        return PageResultFactory.createPageResult(sysRolePage);
    }


    @Override
    public List<Code> findList(CodeRequest codeRequest){
        LambdaQueryWrapper<Code> wrapper = this.createWrapper(codeRequest);
        return this.list(wrapper);
    }

    @Override
    public List<Code> findCodeList(String site){
        LambdaQueryWrapper<Code> wrapper = this.createWrapper(site);
        return this.list(wrapper);
    }


    /** *获取code信息   */
    private Code queryCode(CodeRequest codeRequest){
        Code code = this.getById(codeRequest.getId());
        if(ObjectUtil.isEmpty(code)){
            throw new BusinessException(ResourceExceptionEnum.RESOURCE_NOT_EXISTED);
        }
        return code;
    }

    /** * 创建查询wrapper  */
    private LambdaQueryWrapper<Code> createWrapper(CodeRequest codeRequest){
        LambdaQueryWrapper<Code> queryWrapper = new LambdaQueryWrapper<>();

        String codeName = codeRequest.getName();
        queryWrapper.like(ObjectUtil.isNotEmpty(codeName),Code::getName,codeName);

        /** * 根据时间倒序排列  */
        queryWrapper.orderByAsc(Code::getSite,Code::getCreateTime);

        return queryWrapper;
    }

    /** * 创建查询wrapper  */
    private LambdaQueryWrapper<Code> createWrapper(String site){
        LambdaQueryWrapper<Code> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapper.eq(Code::getSite, site);

        /** * 根据时间倒序排列  */
        queryWrapper.orderByAsc(Code::getCreateTime);

        return queryWrapper;
    }
}
