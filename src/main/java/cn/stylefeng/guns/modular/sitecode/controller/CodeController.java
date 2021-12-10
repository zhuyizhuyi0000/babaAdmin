package cn.stylefeng.guns.modular.sitecode.controller;

import cn.stylefeng.guns.modular.sitecode.pojo.CodeRequest;
import cn.stylefeng.guns.modular.sitecode.service.CNService;
import cn.stylefeng.guns.modular.sitecode.service.CodeService;
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
 * code管理控制器
 */
@RestController
@ApiResource(name = "code管理")
public class CodeController {
    @Resource
    private CodeService codeService;
    @Resource
    private CNService cnService;

    //添加
    @PostResource(name = "添加code",path = "/code_url/add")
    public ResponseData add(@RequestBody @Validated(CodeRequest.add.class) CodeRequest codeRequest){
        Long id = codeService.add(codeRequest);
//        System.out.println(id);
        return new SuccessResponseData();
    }

    /** *删除   */
    @PostResource(name = "删除code",path = "/code_url/delete")
    public ResponseData delete(@RequestBody @Validated(CodeRequest.delete.class) CodeRequest codeRequest){
        Long id = codeService.del(codeRequest);
        return new SuccessResponseData();
    }

    /** *编辑   */
    @PostResource(name = "编辑code", path = "/code_url/edit")
    public ResponseData edit(@RequestBody @Validated(CodeRequest.edit.class) CodeRequest codeRequest) {
        Long id = codeService.edit(codeRequest);
        return new SuccessResponseData();
    }

    /** *查看code详情   */
    @GetResource(name = "查看code详情", path = "/code_url/detail")
    public ResponseData detail(@Validated(CodeRequest.detail.class) CodeRequest codeRequest) {
        return new SuccessResponseData(codeService.detail(codeRequest));
    }

    /** *查询code列表   */
    @GetResource(name = "获取code列表", path = "/code_url/findList")
    public ResponseData list(CodeRequest codeRequest) {
        return new SuccessResponseData(codeService.findList(codeRequest));
    }

    /** *分页查询code列表   */
    @GetResource(name = "分页查询", path = "/code_url/findPage")
    public ResponseData page(CodeRequest codeRequest) {
        return new SuccessResponseData(codeService.findPage(codeRequest));
    }

    /** *test   */
    @GetResource(name = "test查询", path = "/code_url/findPageO")
    public ResponseData findPageDetail(String mode) {
        return new SuccessResponseData(cnService.findPageDetail(mode));
    }
}
