package cn.stylefeng.guns.modular.account.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.stylefeng.guns.core.exception.BusinessException;
import cn.stylefeng.guns.modular.account.entity.Dev;
import cn.stylefeng.guns.modular.account.exception.ResourceExceptionEnum;
import cn.stylefeng.guns.modular.account.mapper.DevMapper;
import cn.stylefeng.guns.modular.account.pojo.DevRequest;
import cn.stylefeng.guns.modular.account.service.DevService;
import cn.stylefeng.roses.kernel.db.api.factory.PageFactory;
import cn.stylefeng.roses.kernel.db.api.factory.PageResultFactory;
import cn.stylefeng.roses.kernel.db.api.pojo.page.PageResult;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/** * dev管理业务实现层   */
@Service
public class DevServiceImpl extends ServiceImpl<DevMapper, Dev> implements DevService {

    @Override
    public  void add(DevRequest devRequest){
        Dev dev = new Dev();
        BeanUtil.copyProperties(devRequest,dev);
        this.save(dev);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void del(DevRequest devRequest){
        Dev dev = this.queryDev(devRequest);
        this.removeById(dev.getId());
    }

    @Override
    public void edit(DevRequest devRequest){
        Dev dev=this.queryDev(devRequest);
        BeanUtil.copyProperties(devRequest,dev);
        this.updateById(dev);
    }

    @Override
    public Dev detail(DevRequest devRequest){
        return this.queryDev(devRequest);
    }

    @Override
    public PageResult<Dev> findPage(DevRequest devRequest){
        LambdaQueryWrapper<Dev> wrapper = createWrapper(devRequest);
        Page<Dev> sysRolePage = this.page(PageFactory.defaultPage(),wrapper);
        return PageResultFactory.createPageResult(sysRolePage);
    }

    @Override
    public List<Dev> findList(DevRequest devRequest){
        LambdaQueryWrapper<Dev> wrapper = this.createWrapper(devRequest);
        return this.list(wrapper);
    }

    /** *获取dev信息   */
    private Dev queryDev(DevRequest devRequest){
        Dev dev = this.getById(devRequest.getId());
        if(ObjectUtil.isEmpty(dev)){
            throw new BusinessException(ResourceExceptionEnum.VIDEO_NOT_EXISTED);
        }
        return dev;
    }

    /** * 创建查询wrapper  */
    private LambdaQueryWrapper<Dev> createWrapper(DevRequest devRequest){
        LambdaQueryWrapper<Dev> queryWrapper = new LambdaQueryWrapper<>();

        String devName = devRequest.getName();
        queryWrapper.like(ObjectUtil.isNotEmpty(devName),Dev::getName,devName);

        /** * 根据时间倒序排列  */
        queryWrapper.orderByDesc(Dev::getCreateTime);

        return queryWrapper;
    }
}
