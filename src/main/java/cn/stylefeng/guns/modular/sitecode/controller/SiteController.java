package cn.stylefeng.guns.modular.sitecode.controller;

import cn.stylefeng.guns.modular.sitecode.pojo.CodeRequest;
import cn.stylefeng.guns.modular.sitecode.service.CNService;
import cn.stylefeng.guns.modular.sitecode.service.CodeService;
import cn.stylefeng.roses.kernel.rule.pojo.response.ResponseData;
import cn.stylefeng.roses.kernel.rule.pojo.response.SuccessResponseData;
import cn.stylefeng.roses.kernel.scanner.api.annotation.ApiResource;
import cn.stylefeng.roses.kernel.scanner.api.annotation.GetResource;
import cn.stylefeng.roses.kernel.scanner.api.annotation.PostResource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * code管理控制器
 */
@RestController
@ApiResource(name = "code管理")
public class SiteController {
    @Resource
    private CNService cnService;

    /** *cn site code   */
    @GetResource(name = "test查询", path = "/code_cn/findPageO")
    public ResponseData findPageDetail(String mode) {
        return new SuccessResponseData(cnService.findPageDetail(mode));
    }
}
