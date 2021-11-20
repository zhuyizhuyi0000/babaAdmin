package cn.stylefeng.guns.modular.resource.controller;

import cn.stylefeng.guns.modular.resource.pojo.TagDocRequest;
import cn.stylefeng.guns.modular.resource.service.TagDocService;
import cn.stylefeng.guns.modular.resource.service.TagLinkDocService;
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
 * 文档标签管理控制器
 */
@RestController
@ApiResource(name = "文档标签管理")
public class TagDocController {
    @Resource
    private TagDocService tagdocService;
    @Resource
    private TagLinkDocService tagLinkDocService;

    //添加
    @PostResource(name = "添加文档标签",path = "/tagDoc/add")
    public ResponseData add(@RequestBody @Validated(TagDocRequest.add.class) TagDocRequest tagdocRequest){
        tagdocService.add(tagdocRequest);
        return new SuccessResponseData();
    }

    /** *删除   */
    @PostResource(name = "删除文档标签",path = "/tagDoc/delete")
    public ResponseData delete(@RequestBody @Validated(TagDocRequest.delete.class) TagDocRequest tagdocRequest){
        Long id = tagdocService.del(tagdocRequest);
        tagLinkDocService.del(id,2); //1 resource；2tag
        return new SuccessResponseData();
    }

    /** *编辑   */
    @PostResource(name = "编辑文档标签", path = "/tagDoc/edit")
    public ResponseData edit(@RequestBody @Validated(TagDocRequest.edit.class) TagDocRequest tagdocRequest) {
        tagdocService.edit(tagdocRequest);
        return new SuccessResponseData();
    }

    /** *查看文档标签详情   */
    @GetResource(name = "查看文档标签详情", path = "/tagDoc/detail")
    public ResponseData detail(@Validated(TagDocRequest.detail.class) TagDocRequest tagdocRequest) {
        return new SuccessResponseData(tagdocService.detail(tagdocRequest));
    }

    /** *查询文档标签列表   */
    @GetResource(name = "获取文档标签列表", path = "/tagDoc/findList")
    public ResponseData list(TagDocRequest tagdocRequest) {
        return new SuccessResponseData(tagdocService.findList(tagdocRequest));
    }

    /** *分页查询文档标签列表   */
    @GetResource(name = "分页查询", path = "/tagDoc/findPage")
    public ResponseData page(TagDocRequest tagdocRequest) {
        return new SuccessResponseData(tagdocService.findPage(tagdocRequest));
    }
}
