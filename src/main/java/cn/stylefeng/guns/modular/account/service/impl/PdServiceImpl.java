package cn.stylefeng.guns.modular.account.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.stylefeng.guns.core.exception.BusinessException;
import cn.stylefeng.guns.modular.account.entity.Pd;
import cn.stylefeng.guns.modular.account.exception.ResourceExceptionEnum;
import cn.stylefeng.guns.modular.account.mapper.PdMapper;
import cn.stylefeng.guns.modular.account.pojo.PdRequest;
import cn.stylefeng.guns.modular.account.service.PdService;
import cn.stylefeng.roses.kernel.db.api.factory.PageFactory;
import cn.stylefeng.roses.kernel.db.api.factory.PageResultFactory;
import cn.stylefeng.roses.kernel.db.api.pojo.page.PageResult;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/** * pd管理业务实现层   */
@Service
public class PdServiceImpl extends ServiceImpl<PdMapper, Pd> implements PdService {

    @Override
    public  void add(PdRequest pdRequest){
        Pd pd = new Pd();
        BeanUtil.copyProperties(pdRequest,pd);
        this.save(pd);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void del(PdRequest pdRequest){
        Pd pd = this.queryPd(pdRequest);
        this.removeById(pd.getId());
    }

    @Override
    public void edit(PdRequest pdRequest){
        Pd pd=this.queryPd(pdRequest);
        BeanUtil.copyProperties(pdRequest,pd);
        this.updateById(pd);
    }

    @Override
    public Pd detail(PdRequest pdRequest){
        return this.queryPd(pdRequest);
    }

    @Override
    public PageResult<Pd> findPage(PdRequest pdRequest){
        LambdaQueryWrapper<Pd> wrapper = createWrapper(pdRequest);
        Page<Pd> sysRolePage = this.page(PageFactory.defaultPage(),wrapper);
        return PageResultFactory.createPageResult(sysRolePage);
    }

    @Override
    public List<Pd> findList(PdRequest pdRequest){
        LambdaQueryWrapper<Pd> wrapper = this.createWrapper(pdRequest);
        return this.list(wrapper);
    }

    /** *获取pd信息   */
    private Pd queryPd(PdRequest pdRequest){
        Pd pd = this.getById(pdRequest.getId());
        if(ObjectUtil.isEmpty(pd)){
            throw new BusinessException(ResourceExceptionEnum.VIDEO_NOT_EXISTED);
        }
        return pd;
    }

    /** * 创建查询wrapper  */
    private LambdaQueryWrapper<Pd> createWrapper(PdRequest pdRequest){
        LambdaQueryWrapper<Pd> queryWrapper = new LambdaQueryWrapper<>();

        String pdName = pdRequest.getName();
        queryWrapper.like(ObjectUtil.isNotEmpty(pdName),Pd::getName,pdName);

        /** * 根据时间倒序排列  */
        queryWrapper.orderByDesc(Pd::getCreateTime);

        return queryWrapper;
    }
}
