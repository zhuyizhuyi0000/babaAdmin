package cn.stylefeng.guns.modular.business.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.stylefeng.guns.core.exception.BusinessException;
import cn.stylefeng.guns.modular.business.controller.CarController;
import cn.stylefeng.guns.modular.business.entity.Car;
import cn.stylefeng.guns.modular.business.exception.CarExceptionEnum;
import cn.stylefeng.guns.modular.business.mapper.CarMapper;
import cn.stylefeng.guns.modular.business.pojo.CarRequest;
import cn.stylefeng.guns.modular.business.service.CarService;
import cn.stylefeng.roses.kernel.db.api.factory.PageFactory;
import cn.stylefeng.roses.kernel.db.api.factory.PageResultFactory;
import cn.stylefeng.roses.kernel.db.api.pojo.page.PageResult;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/** * 车辆管理业务实现层   */
@Service
public class CarServiceImpl extends ServiceImpl<CarMapper, Car> implements CarService {

    @Override
    public  void add(CarRequest carRequest){
        Car car = new Car();
        BeanUtil.copyProperties(carRequest,car);
        this.save(car);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void del(CarRequest carRequest){
        Car car = this.queryCar(carRequest);
        this.removeById(car.getCarId());
    }

    @Override
    public void edit(CarRequest carRequest){
        Car car=this.queryCar(carRequest);
        BeanUtil.copyProperties(carRequest,car);
        this.updateById(car);
    }

    @Override
    public Car detail(CarRequest carRequest){
        return this.queryCar(carRequest);
    }

    @Override
    public PageResult<Car> findPage(CarRequest carRequest){
        LambdaQueryWrapper<Car> wrapper = createWrapper(carRequest);
        Page<Car> sysRolePage = this.page(PageFactory.defaultPage(),wrapper);
        return PageResultFactory.createPageResult(sysRolePage);
    }

    @Override
    public List<Car> findList(CarRequest carRequest){
        LambdaQueryWrapper<Car> wrapper = this.createWrapper(carRequest);
        return this.list(wrapper);
    }

    /** *获取车辆信息   */
    private Car queryCar(CarRequest carRequest){
        Car car = this.getById(carRequest.getCarId());
        if(ObjectUtil.isEmpty(car)){
            throw new BusinessException(CarExceptionEnum.CAR_NOT_EXISTED);
        }
        return car;
    }

    /** * 创建查询wrapper  */
    private LambdaQueryWrapper<Car> createWrapper(CarRequest carRequest){
        LambdaQueryWrapper<Car> queryWrapper = new LambdaQueryWrapper<>();

        String carName = carRequest.getCarName();
        queryWrapper.like(ObjectUtil.isNotEmpty(carName),Car::getCarName,carName);

        /** * 根据时间倒序排列  */
        queryWrapper.orderByDesc(Car::getCreateTime);

        return queryWrapper;
    }
}
