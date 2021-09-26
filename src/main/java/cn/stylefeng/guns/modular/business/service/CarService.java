package cn.stylefeng.guns.modular.business.service;

import cn.stylefeng.guns.modular.business.entity.Car;
import cn.stylefeng.guns.modular.business.pojo.CarRequest;
import cn.stylefeng.roses.kernel.db.api.pojo.page.PageResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface CarService extends IService<Car> {
    /** *添加车辆   */
    void add(CarRequest carRequest);

    /** *删除车辆   */
    void del(CarRequest carRequest);

    /** *编辑车辆   */
    void edit(CarRequest carRequest);

    /** *查看详情   */
    Car detail(CarRequest carRequest);

    /** *分页查询   */
    PageResult<Car> findPage(CarRequest carRequest);

    /** *查询所有车辆   */
    List<Car> findList(CarRequest carRequest);
}
