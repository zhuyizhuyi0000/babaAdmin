package cn.stylefeng.guns.modular.tool.controller;

import cn.stylefeng.roses.kernel.scanner.api.annotation.ApiResource;
import cn.stylefeng.roses.kernel.scanner.api.annotation.GetResource;
import org.springframework.stereotype.Controller;


/**
 * tool管理界面
 */
@Controller
@ApiResource(name = "tool管理首页")
public class ToolbacktimeViewController {

    /**
     * tool管理-编辑
     */
    @GetResource(name = "tool管理-编辑", path = "/view/tool_backtime")
    public String toolEdit() {
        return "/modular/tool/backtime/tool_backtime.html";
    }
}
