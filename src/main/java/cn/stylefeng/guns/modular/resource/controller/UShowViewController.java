package cn.stylefeng.guns.modular.resource.controller;

import cn.stylefeng.roses.kernel.scanner.api.annotation.ApiResource;
import cn.stylefeng.roses.kernel.scanner.api.annotation.GetResource;
import org.springframework.stereotype.Controller;


/**
 * uShow管理界面
 */
@Controller
@ApiResource(name = "uShow管理首页")
public class UShowViewController {
    //    uShow管理首页
    @GetResource(name ="uShow管理首页", path="/view/res_ushow")
    public String ushowIndex(){
        return "/modular/resource/ushow/res_ushow.html";
    }

    /**
     * uShow管理-新增
     */
    @GetResource(name = "uShow管理-新增", path = "/view/res_ushow/add")
    public String ushowAdd() {
        return "/modular/resource/ushow/res_ushow_add.html";
    }

    /**
     * uShow管理-编辑
     */
    @GetResource(name = "uShow管理-编辑", path = "/view/res_ushow/edit")
    public String ushowEdit() {
        return "/modular/resource/ushow/res_ushow_edit.html";
    }
}
