package cn.stylefeng.guns.modular.resource.controller;

import cn.stylefeng.guns.modular.resource.pojo.TagVideoRequest;
import cn.stylefeng.guns.modular.resource.service.TagLinkVideoService;
import cn.stylefeng.guns.modular.resource.service.TagVideoService;
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
 * 视频标签管理控制器
 */
@RestController
@ApiResource(name = "视频标签管理")
public class TagVideoController {
    @Resource
    private TagVideoService tagvideoService;
    @Resource
    private TagLinkVideoService tagLinkVideoService;

    //添加
    @PostResource(name = "添加视频标签",path = "/tagVideo/add")
    public ResponseData add(@RequestBody @Validated(TagVideoRequest.add.class) TagVideoRequest tagvideoRequest){
        tagvideoService.add(tagvideoRequest);
        return new SuccessResponseData();
    }

    /** *删除   */
    @PostResource(name = "删除视频标签",path = "/tagVideo/delete")
    public ResponseData delete(@RequestBody @Validated(TagVideoRequest.delete.class) TagVideoRequest tagvideoRequest){
        Long id=tagvideoService.del(tagvideoRequest);
        tagLinkVideoService.del(id,2); //1 resource；2tag
        return new SuccessResponseData();
    }

    /** *编辑   */
    @PostResource(name = "编辑视频标签", path = "/tagVideo/edit")
    public ResponseData edit(@RequestBody @Validated(TagVideoRequest.edit.class) TagVideoRequest tagvideoRequest) {
        tagvideoService.edit(tagvideoRequest);
        return new SuccessResponseData();
    }

    /** *查看视频标签详情   */
    @GetResource(name = "查看视频标签详情", path = "/tagVideo/detail")
    public ResponseData detail(@Validated(TagVideoRequest.detail.class) TagVideoRequest tagvideoRequest) {
        return new SuccessResponseData(tagvideoService.detail(tagvideoRequest));
    }

    /** *查询视频标签列表   */
    @GetResource(name = "获取视频标签列表", path = "/tagVideo/findList")
    public ResponseData list(TagVideoRequest tagvideoRequest) {
        return new SuccessResponseData(tagvideoService.findList(tagvideoRequest));
    }

    /** *分页查询视频标签列表   */
    @GetResource(name = "分页查询", path = "/tagVideo/findPage")
    public ResponseData page(TagVideoRequest tagvideoRequest) {
        return new SuccessResponseData(tagvideoService.findPage(tagvideoRequest));
    }
}
