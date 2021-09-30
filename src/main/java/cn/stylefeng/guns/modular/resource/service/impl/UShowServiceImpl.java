package cn.stylefeng.guns.modular.resource.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.stylefeng.guns.core.exception.BusinessException;
import cn.stylefeng.guns.modular.resource.entity.UShow;
import cn.stylefeng.guns.modular.resource.exception.ResourceExceptionEnum;
import cn.stylefeng.guns.modular.resource.mapper.UShowMapper;
import cn.stylefeng.guns.modular.resource.pojo.UShowRequest;
import cn.stylefeng.guns.modular.resource.service.UShowService;
import cn.stylefeng.roses.kernel.db.api.factory.PageFactory;
import cn.stylefeng.roses.kernel.db.api.factory.PageResultFactory;
import cn.stylefeng.roses.kernel.db.api.pojo.page.PageResult;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/** * uShow管理业务实现层   */
@Service
public class UShowServiceImpl extends ServiceImpl<UShowMapper, UShow> implements UShowService {

    @Override
    public  void add(UShowRequest ushowRequest){
        UShow ushow = new UShow();
        BeanUtil.copyProperties(ushowRequest,ushow);
        this.save(ushow);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void del(UShowRequest ushowRequest){
        UShow ushow = this.queryUShow(ushowRequest);
        this.removeById(ushow.getUshowId());
    }

    @Override
    public void edit(UShowRequest ushowRequest){
        UShow ushow=this.queryUShow(ushowRequest);
        BeanUtil.copyProperties(ushowRequest,ushow);
        this.updateById(ushow);
    }

    @Override
    public UShow detail(UShowRequest ushowRequest){
        return this.queryUShow(ushowRequest);
    }

    @Override
    public PageResult<UShow> findPage(UShowRequest ushowRequest){
        LambdaQueryWrapper<UShow> wrapper = createWrapper(ushowRequest);
        Page<UShow> sysRolePage = this.page(PageFactory.defaultPage(),wrapper);
        return PageResultFactory.createPageResult(sysRolePage);
    }

    @Override
    public List<UShow> findList(UShowRequest ushowRequest){
        LambdaQueryWrapper<UShow> wrapper = this.createWrapper(ushowRequest);
        return this.list(wrapper);
    }

    /** *获取uShow信息   */
    private UShow queryUShow(UShowRequest ushowRequest){
        UShow ushow = this.getById(ushowRequest.getUshowId());
        if(ObjectUtil.isEmpty(ushow)){
            throw new BusinessException(ResourceExceptionEnum.VIDEO_NOT_EXISTED);
        }
        return ushow;
    }

    /** * 创建查询wrapper  */
    private LambdaQueryWrapper<UShow> createWrapper(UShowRequest ushowRequest){
        LambdaQueryWrapper<UShow> queryWrapper = new LambdaQueryWrapper<>();

        String ushowName = ushowRequest.getFileName();
        queryWrapper.like(ObjectUtil.isNotEmpty(ushowName),UShow::getFileName,ushowName);

        /** * 根据时间倒序排列  */
        queryWrapper.orderByDesc(UShow::getCreateTime);

        return queryWrapper;
    }
}
