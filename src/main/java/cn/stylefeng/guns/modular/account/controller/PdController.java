package cn.stylefeng.guns.modular.account.controller;


import cn.stylefeng.guns.modular.account.pojo.PdRequest;
import cn.stylefeng.guns.modular.account.service.PdService;
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
 * pd管理控制器
 */
@RestController
@ApiResource(name = "pd管理")
public class PdController {
    @Resource
    private PdService pdService;

    //添加
    @PostResource(name = "添加pd",path = "/pd/add")
    public ResponseData add(@RequestBody @Validated(PdRequest.add.class) PdRequest pdRequest){
        pdService.add(pdRequest);
        return new SuccessResponseData();
    }

    /** *删除   */
    @PostResource(name = "删除pd",path = "/pd/delete")
    public ResponseData delete(@RequestBody @Validated(PdRequest.delete.class) PdRequest pdRequest){
        pdService.del(pdRequest);
        return new SuccessResponseData();
    }

    /** *编辑   */
    @PostResource(name = "编辑pd", path = "/pd/edit")
    public ResponseData edit(@RequestBody @Validated(PdRequest.edit.class) PdRequest pdRequest) {
        pdService.edit(pdRequest);
        return new SuccessResponseData();
    }

    /** *查看pd详情   */
    @GetResource(name = "查看pd详情", path = "/pd/detail")
    public ResponseData detail(@Validated(PdRequest.detail.class) PdRequest pdRequest) {
        return new SuccessResponseData(pdService.detail(pdRequest));
    }

    /** *查询pd列表   */
    @GetResource(name = "获取pd列表", path = "/pd/findList")
    public ResponseData list(PdRequest pdRequest) {
        return new SuccessResponseData(pdService.findList(pdRequest));
    }

    /** *分页查询pd列表   */
    @GetResource(name = "分页查询", path = "/pd/findPage")
    public ResponseData page(PdRequest pdRequest) {
        return new SuccessResponseData(pdService.findPage(pdRequest));
    }
}
