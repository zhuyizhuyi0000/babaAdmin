package cn.stylefeng.guns.modular.account.pojo;

import cn.stylefeng.roses.kernel.rule.pojo.request.BaseRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/** *pd管理请求   */
@EqualsAndHashCode(callSuper = true)
@Data
public class PdRequest extends BaseRequest {
    /** * id  */
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
