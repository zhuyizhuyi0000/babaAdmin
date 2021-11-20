package cn.stylefeng.guns.modular.resource.controller;

import cn.stylefeng.guns.modular.resource.pojo.DocRequest;
import cn.stylefeng.guns.modular.resource.pojo.DocRequest;
import cn.stylefeng.guns.modular.resource.pojo.OnlineRequest;
import cn.stylefeng.guns.modular.resource.service.DocService;
import cn.stylefeng.guns.modular.resource.service.TagLinkDocService;
import cn.stylefeng.guns.modular.resource.service.DocService;
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
 * 文档管理控制器
 */
@RestController
@ApiResource(name = "文档管理")
public class DocController {
    @Resource
    private DocService docService;
    @Resource
    private TagLinkDocService tagLinkDocService;

    //添加
    @PostResource(name = "添加文档",path = "/doc/add")
    public ResponseData add(@RequestBody @Validated(DocRequest.add.class) DocRequest docRequest){
        Long id = docService.add(docRequest);
        return new SuccessResponseData();
    }

    /** *删除   */
    @PostResource(name = "删除文档",path = "/doc/delete")
    public ResponseData delete(@RequestBody @Validated(DocRequest.delete.class) DocRequest docRequest){
        Long id = docService.del(docRequest);
        return new SuccessResponseData();
    }

    /** *编辑   */
    @PostResource(name = "编辑文档", path = "/doc/edit")
    public ResponseData edit(@RequestBody @Validated(DocRequest.edit.class) DocRequest docRequest) {
        Long id = docService.edit(docRequest);
        return new SuccessResponseData();
    }

    /** *查看文档详情   */
    @GetResource(name = "查看文档详情", path = "/doc/detail")
    public ResponseData detail(@Validated(DocRequest.detail.class) DocRequest docRequest) {
        return new SuccessResponseData(docService.detail(docRequest));
    }

    /** *查询文档列表   */
    @GetResource(name = "获取文档列表", path = "/doc/findList")
    public ResponseData list(DocRequest docRequest) {
        return new SuccessResponseData(docService.findList(docRequest));
    }

    /** *分页查询文档列表   */
    @GetResource(name = "分页查询", path = "/doc/findPage")
    public ResponseData page(DocRequest docRequest) {
        return new SuccessResponseData(docService.findPage(docRequest));
    }

    /** *传账号密码去查询资源列表   */
    @GetResource(name = "批量查询资源", path = "/doc/findPageDetail")
    public ResponseData pageDetail(OnlineRequest onlineRequest) {
        return new SuccessResponseData(docService.findPageDetail(onlineRequest));
    }

    /** *传账号密码去查询一个资源   */
    @GetResource(name = "单个查询资源", path = "/doc/findPageOneDetail")
    public ResponseData pageOneDetail(OnlineRequest onlineRequest) {
        return new SuccessResponseData(docService.findPageOneDetail(onlineRequest));
    }
}
