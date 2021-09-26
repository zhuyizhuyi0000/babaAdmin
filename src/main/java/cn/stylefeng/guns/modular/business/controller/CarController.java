package cn.stylefeng.guns.modular.business.controller;

import cn.stylefeng.guns.modular.business.pojo.CarRequest;
import cn.stylefeng.guns.modular.business.service.CarService;
import cn.stylefeng.roses.kernel.rule.pojo.request.BaseRequest;
import cn.stylefeng.roses.kernel.rule.pojo.response.ResponseData;
import cn.stylefeng.roses.kernel.rule.pojo.response.SuccessResponseData;
import cn.stylefeng.roses.kernel.scanner.api.annotation.ApiResource;
import cn.stylefeng.roses.kernel.scanner.api.annotation.GetResource;
import cn.stylefeng.roses.kernel.scanner.api.annotation.PostResource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
/**
 * 车辆管理控制器
 */
@RestController
@ApiResource(name = "车辆管理")
public class CarController {
    @Resource
    private CarService carService;

    //添加
    @PostResource(name = "添加车辆",path = "/car/add")
    public ResponseData add(@RequestBody @Validated(CarRequest.add.class) CarRequest carRequest){
        carService.add(carRequest);
        return new SuccessResponseData();
    }

    /** *删除   */
    @PostResource(name = "删除车辆",path = "/car/delete")
    public ResponseData delete(@RequestBody @Validated(CarRequest.delete.class) CarRequest carRequest){
        carService.del(carRequest);
        return new SuccessResponseData();
    }

    /** *编辑   */
    @PostResource(name = "编辑车辆", path = "/car/edit")
    public ResponseData edit(@RequestBody @Validated(CarRequest.edit.class) CarRequest carRequest) {
        carService.edit(carRequest);
        return new SuccessResponseData();
    }

    /** *查看车辆详情   */
    @GetResource(name = "查看车辆详情", path = "/car/detail")
    public ResponseData detail(@Validated(CarRequest.detail.class) CarRequest carRequest) {
        return new SuccessResponseData(carService.detail(carRequest));
    }

    /** *查询车辆列表   */
    @GetResource(name = "获取车辆列表", path = "/car/findList")
    public ResponseData list(CarRequest carRequest) {
        return new SuccessResponseData(carService.findList(carRequest));
    }

    /** *分页查询车辆列表   */
    @GetResource(name = "分页查询", path = "/car/findPage")
    public ResponseData page(CarRequest carRequest) {
        return new SuccessResponseData(carService.findPage(carRequest));
    }
}
