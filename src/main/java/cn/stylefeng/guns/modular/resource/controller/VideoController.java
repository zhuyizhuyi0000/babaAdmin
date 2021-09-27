package cn.stylefeng.guns.modular.resource.controller;

import cn.stylefeng.guns.modular.resource.pojo.VideoRequest;
import cn.stylefeng.guns.modular.resource.service.VideoService;
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
 * 视频管理控制器
 */
@RestController
@ApiResource(name = "视频管理")
public class VideoController {
    @Resource
    private VideoService videoService;

    //添加
    @PostResource(name = "添加视频",path = "/video/add")
    public ResponseData add(@RequestBody @Validated(VideoRequest.add.class) VideoRequest videoRequest){
        videoService.add(videoRequest);
        return new SuccessResponseData();
    }

    /** *删除   */
    @PostResource(name = "删除视频",path = "/video/delete")
    public ResponseData delete(@RequestBody @Validated(VideoRequest.delete.class) VideoRequest videoRequest){
        videoService.del(videoRequest);
        return new SuccessResponseData();
    }

    /** *编辑   */
    @PostResource(name = "编辑视频", path = "/video/edit")
    public ResponseData edit(@RequestBody @Validated(VideoRequest.edit.class) VideoRequest videoRequest) {
        videoService.edit(videoRequest);
        return new SuccessResponseData();
    }

    /** *查看视频详情   */
    @GetResource(name = "查看视频详情", path = "/video/detail")
    public ResponseData detail(@Validated(VideoRequest.detail.class) VideoRequest videoRequest) {
        return new SuccessResponseData(videoService.detail(videoRequest));
    }

    /** *查询视频列表   */
    @GetResource(name = "获取视频列表", path = "/video/findList")
    public ResponseData list(VideoRequest videoRequest) {
        return new SuccessResponseData(videoService.findList(videoRequest));
    }

    /** *分页查询视频列表   */
    @GetResource(name = "分页查询", path = "/video/findPage")
    public ResponseData page(VideoRequest videoRequest) {
        return new SuccessResponseData(videoService.findPage(videoRequest));
    }
}
