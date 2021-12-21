package cn.stylefeng.guns.modular.account.pojo;

import cn.stylefeng.roses.kernel.rule.pojo.request.BaseRequest;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;

/** *dev管理请求   */
@EqualsAndHashCode(callSuper = true)
@Data
public class DevRequest extends BaseRequest {
    /** * devid  */
    @NotNull(message = "id不能为空",groups = {edit.class,delete.class,detail.class})
    private Long id;

    /** * dev名称  */
    @NotNull(message = "名称不能为空",groups = {add.class,edit.class})
    private String name;

    /** * env  */
    private String env;

    /** * loginname */
    private String loginName;

    /** * password  */
    private String password;

    /** * description  */
    private String description;

}
