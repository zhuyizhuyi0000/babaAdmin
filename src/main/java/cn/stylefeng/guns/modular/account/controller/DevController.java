package cn.stylefeng.guns.modular.account.controller;


import cn.stylefeng.guns.modular.account.pojo.DevRequest;
import cn.stylefeng.guns.modular.account.service.DevService;
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
 * dev管理控制器
 */
@RestController
@ApiResource(name = "dev管理")
public class DevController {
    @Resource
    private DevService devService;

    //添加
    @PostResource(name = "添加dev",path = "/dev/add")
    public ResponseData add(@RequestBody @Validated(DevRequest.add.class) DevRequest devRequest){
        devService.add(devRequest);
        return new SuccessResponseData();
    }

    /** *删除   */
    @PostResource(name = "删除dev",path = "/dev/delete")
    public ResponseData delete(@RequestBody @Validated(DevRequest.delete.class) DevRequest devRequest){
        devService.del(devRequest);
        return new SuccessResponseData();
    }

    /** *编辑   */
    @PostResource(name = "编辑dev", path = "/dev/edit")
    public ResponseData edit(@RequestBody @Validated(DevRequest.edit.class) DevRequest devRequest) {
        devService.edit(devRequest);
        return new SuccessResponseData();
    }

    /** *查看dev详情   */
    @GetResource(name = "查看dev详情", path = "/dev/detail")
    public ResponseData detail(@Validated(DevRequest.detail.class) DevRequest devRequest) {
        return new SuccessResponseData(devService.detail(devRequest));
    }

    /** *查询dev列表   */
    @GetResource(name = "获取dev列表", path = "/dev/findList")
    public ResponseData list(DevRequest devRequest) {
        return new SuccessResponseData(devService.findList(devRequest));
    }

    /** *分页查询dev列表   */
    @GetResource(name = "分页查询", path = "/dev/findPage")
    public ResponseData page(DevRequest devRequest) {
        return new SuccessResponseData(devService.findPage(devRequest));
    }
}
