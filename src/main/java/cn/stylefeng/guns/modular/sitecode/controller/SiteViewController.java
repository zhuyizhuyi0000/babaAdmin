package cn.stylefeng.guns.modular.sitecode.controller;

import cn.stylefeng.roses.kernel.scanner.api.annotation.ApiResource;
import cn.stylefeng.roses.kernel.scanner.api.annotation.GetResource;
import org.springframework.stereotype.Controller;


/**
 * code管理界面
 */
@Controller
@ApiResource(name = "cn code管理首页")
public class SiteViewController {
    //    cn code管理首页
    @GetResource(name ="code管理首页", path="/view/code_cn")
    public String codeIndex(){
        return "/modular/sitecode/cn/code_cn.html";
    }

}
