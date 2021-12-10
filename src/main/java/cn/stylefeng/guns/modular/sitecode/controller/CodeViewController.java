package cn.stylefeng.guns.modular.sitecode.controller;

import cn.stylefeng.roses.kernel.scanner.api.annotation.ApiResource;
import cn.stylefeng.roses.kernel.scanner.api.annotation.GetResource;
import org.springframework.stereotype.Controller;


/**
 * code管理界面
 */
@Controller
@ApiResource(name = "code管理首页")
public class CodeViewController {
    //    code管理首页
    @GetResource(name ="code管理首页", path="/view/code_url")
    public String codeIndex(){
        return "/modular/sitecode/code/code_url.html";
    }

    /**
     * code管理-新增
     */
    @GetResource(name = "code管理-新增", path = "/view/code_url/add")
    public String codeAdd() {
        return "/modular/sitecode/code/code_url_add.html";
    }

    /**
     * code管理-编辑
     */
    @GetResource(name = "code管理-编辑", path = "/view/code_url/edit")
    public String codeEdit() {
        return "/modular/sitecode/code/code_url_edit.html";
    }
}
