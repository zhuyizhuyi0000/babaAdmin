package cn.stylefeng.guns.modular.resource.controller;

import cn.stylefeng.roses.kernel.scanner.api.annotation.ApiResource;
import cn.stylefeng.roses.kernel.scanner.api.annotation.GetResource;
import org.springframework.stereotype.Controller;


/**
 * 视频标签管理界面
 */
@Controller
@ApiResource(name = "视频标签管理首页")
public class TagVideoViewController {
    //    视频标签管理首页
    @GetResource(name ="视频标签管理首页", path="/view/tagVideo")
    public String tagvideoIndex(){
        return "/modular/resource/tagvideo/tagVideo.html";
    }

    /**
     * 视频标签管理-新增
     */
    @GetResource(name = "视频标签管理-新增", path = "/view/tagVideo/add")
    public String tagvideoAdd() {
        return "/modular/resource/tagvideo/tagVideo_add.html";
    }

    /**
     * 视频标签管理-编辑
     */
    @GetResource(name = "视频标签管理-编辑", path = "/view/tagVideo/edit")
    public String tagvideoEdit() {
        return "/modular/resource/tagvideo/tagVideo_edit.html";
    }
}
