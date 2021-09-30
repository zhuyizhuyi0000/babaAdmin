package cn.stylefeng.guns.modular.resource.controller;

import cn.stylefeng.guns.modular.resource.pojo.UShowRequest;
import cn.stylefeng.guns.modular.resource.service.UShowService;
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
 * uShow管理控制器
 */
@RestController
@ApiResource(name = "uShow管理")
public class UShowController {
    @Resource
    private UShowService ushowService;

    //添加
    @PostResource(name = "添加uShow",path = "/ushow/add")
    public ResponseData add(@RequestBody @Validated(UShowRequest.add.class) UShowRequest ushowRequest){
        ushowService.add(ushowRequest);
        return new SuccessResponseData();
    }

    /** *删除   */
    @PostResource(name = "删除uShow",path = "/ushow/delete")
    public ResponseData delete(@RequestBody @Validated(UShowRequest.delete.class) UShowRequest ushowRequest){
        ushowService.del(ushowRequest);
        return new SuccessResponseData();
    }

    /** *编辑   */
    @PostResource(name = "编辑uShow", path = "/ushow/edit")
    public ResponseData edit(@RequestBody @Validated(UShowRequest.edit.class) UShowRequest ushowRequest) {
        ushowService.edit(ushowRequest);
        return new SuccessResponseData();
    }

    /** *查看uShow详情   */
    @GetResource(name = "查看uShow详情", path = "/ushow/detail")
    public ResponseData detail(@Validated(UShowRequest.detail.class) UShowRequest ushowRequest) {
        return new SuccessResponseData(ushowService.detail(ushowRequest));
    }

    /** *查询uShow列表   */
    @GetResource(name = "获取uShow列表", path = "/ushow/findList")
    public ResponseData list(UShowRequest ushowRequest) {
        return new SuccessResponseData(ushowService.findList(ushowRequest));
    }

    /** *分页查询uShow列表   */
    @GetResource(name = "分页查询", path = "/ushow/findPage")
    public ResponseData page(UShowRequest ushowRequest) {
        return new SuccessResponseData(ushowService.findPage(ushowRequest));
    }
}
