package cn.stylefeng.guns.modular.resource.controller;

import cn.stylefeng.roses.kernel.scanner.api.annotation.ApiResource;
import cn.stylefeng.roses.kernel.scanner.api.annotation.GetResource;
import org.springframework.stereotype.Controller;


/**
 * 文档管理界面
 */
@Controller
@ApiResource(name = "文档管理首页")
public class DocViewController {
    //    文档管理首页
    @GetResource(name ="文档管理首页", path="/view/res_doc")
    public String docIndex(){
        return "/modular/resource/doc/res_doc.html";
    }

    /**
     * 文档管理-新增
     */
    @GetResource(name = "文档管理-新增", path = "/view/res_doc/add")
    public String docAdd() {
        return "/modular/resource/doc/res_doc_add.html";
    }

    /**
     * 文档管理-编辑
     */
    @GetResource(name = "文档管理-编辑", path = "/view/res_doc/edit")
    public String docEdit() {
        return "/modular/resource/doc/res_doc_edit.html";
    }
}
