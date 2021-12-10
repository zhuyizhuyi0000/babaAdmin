package cn.stylefeng.guns.modular.sitecode.pojo;

import cn.stylefeng.roses.kernel.rule.pojo.request.BaseRequest;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;

/** *code管理请求   */
@EqualsAndHashCode(callSuper = true)
@Data
public class CodeRequest extends BaseRequest {
    /** * codeid  */
    @NotNull(message = "codeid不能为空",groups = {edit.class,delete.class,detail.class})
    private Long id;

    /** * code名称  */
    @NotNull(message = "code名称不能为空",groups = {add.class,edit.class})
    private String name;

    /** * code链接  */
    @NotNull(message = "code链接不能为空",groups = {add.class,edit.class})
    private String url;

    /** * code site */
    private String site;

    /** * code描述  */
    private String description;

    /** * code逻辑  */
    private String logic;

}
