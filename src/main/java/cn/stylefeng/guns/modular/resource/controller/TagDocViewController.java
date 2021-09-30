package cn.stylefeng.guns.modular.resource.controller;

import cn.stylefeng.roses.kernel.scanner.api.annotation.ApiResource;
import cn.stylefeng.roses.kernel.scanner.api.annotation.GetResource;
import org.springframework.stereotype.Controller;


/**
 * 文档标签管理界面
 */
@Controller
@ApiResource(name = "文档标签管理首页")
public class TagDocViewController {
    //    文档标签管理首页
    @GetResource(name ="文档标签管理首页", path="/view/tagDoc")
    public String tagdocIndex(){
        return "/modular/resource/tagdoc/tagDoc.html";
    }

    /**
     * 文档标签管理-新增
     */
    @GetResource(name = "文档标签管理-新增", path = "/view/tagDoc/add")
    public String tagdocAdd() {
        return "/modular/resource/tagdoc/tagDoc_add.html";
    }

    /**
     * 文档标签管理-编辑
     */
    @GetResource(name = "文档标签管理-编辑", path = "/view/tagDoc/edit")
    public String tagdocEdit() {
        return "/modular/resource/tagdoc/tagDoc_edit.html";
    }
}
