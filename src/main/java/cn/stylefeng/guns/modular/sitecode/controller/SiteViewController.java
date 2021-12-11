package cn.stylefeng.guns.modular.sitecode.controller;

import cn.stylefeng.roses.kernel.scanner.api.annotation.ApiResource;
import cn.stylefeng.roses.kernel.scanner.api.annotation.GetResource;
import org.springframework.stereotype.Controller;


/**
 * code管理界面
 */
@Controller
@ApiResource(name = "code管理首页")
public class SiteViewController {
    //    cn code管理首页
    @GetResource(name ="code管理首页", path="/view/code_cn")
    public String codeIndex(){
        return "/modular/sitecode/cn/code_cn.html";
    }

    //    com code管理首页
    @GetResource(name ="code管理首页", path="/view/code_com")
    public String codeIndex1(){
        return "/modular/sitecode/com/code_com.html";
    }

    //    co code管理首页
    @GetResource(name ="code管理首页", path="/view/code_co")
    public String codeIndex2(){
        return "/modular/sitecode/co/code_co.html";
    }

    //    tw code管理首页
    @GetResource(name ="code管理首页", path="/view/code_tw")
    public String codeIndex3(){
        return "/modular/sitecode/tw/code_tw.html";
    }

}
