package cn.stylefeng.guns.modular.account.controller;

import cn.stylefeng.roses.kernel.scanner.api.annotation.ApiResource;
import cn.stylefeng.roses.kernel.scanner.api.annotation.GetResource;
import org.springframework.stereotype.Controller;


/**
 * pd管理界面
 */
@Controller
@ApiResource(name = "pd管理首页")
public class PdViewController {
    //    pd管理首页
    @GetResource(name ="pd管理首页", path="/view/acc_pd")
    public String pdIndex(){
        return "/modular/account/pd/acc_pd.html";
    }

    /**
     * pd管理-新增
     */
    @GetResource(name = "pd管理-新增", path = "/view/acc_pd/add")
    public String pdAdd() {
        return "/modular/account/pd/acc_pd_add.html";
    }

    /**
     * pd管理-编辑
     */
    @GetResource(name = "pd管理-编辑", path = "/view/acc_pd/edit")
    public String pdEdit() {
        return "/modular/account/pd/acc_pd_edit.html";
    }
}
