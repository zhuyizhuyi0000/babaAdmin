package cn.stylefeng.guns.modular.resource.controller;

import cn.stylefeng.roses.kernel.scanner.api.annotation.ApiResource;
import cn.stylefeng.roses.kernel.scanner.api.annotation.GetResource;
import org.springframework.stereotype.Controller;


/**
 * 视频管理界面
 */
@Controller
@ApiResource(name = "视频管理首页")
public class VideoViewController {
    //    视频管理首页
    @GetResource(name ="视频管理首页", path="/view/res_video")
    public String videoIndex(){
        return "/modular/resource/video/res_video.html";
    }

    /**
     * 视频管理-新增
     */
    @GetResource(name = "视频管理-新增", path = "/view/res_video/add")
    public String videoAdd() {
        return "/modular/resource/video/res_video_add.html";
    }

    /**
     * 视频管理-编辑
     */
    @GetResource(name = "视频管理-编辑", path = "/view/res_video/edit")
    public String videoEdit() {
        return "/modular/resource/video/res_video_edit.html";
    }
}
