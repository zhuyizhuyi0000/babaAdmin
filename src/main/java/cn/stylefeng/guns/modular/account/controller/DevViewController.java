package cn.stylefeng.guns.modular.account.controller;

import cn.stylefeng.roses.kernel.scanner.api.annotation.ApiResource;
import cn.stylefeng.roses.kernel.scanner.api.annotation.GetResource;
import org.springframework.stereotype.Controller;


/**
 * dev管理界面
 */
@Controller
@ApiResource(name = "dev管理首页")
public class DevViewController {
    //    dev管理首页
    @GetResource(name ="dev管理首页", path="/view/acc_dev")
    public String devIndex(){
        return "/modular/account/dev/acc_dev.html";
    }

    /**
     * dev管理-新增
     */
    @GetResource(name = "dev管理-新增", path = "/view/acc_dev/add")
    public String devAdd() {
        return "/modular/account/dev/acc_dev_add.html";
    }

    /**
     * dev管理-编辑
     */
    @GetResource(name = "dev管理-编辑", path = "/view/acc_dev/edit")
    public String devEdit() {
        return "/modular/account/dev/acc_dev_edit.html";
    }
}
