package cn.stylefeng.guns.modular.resource.pojo;

import cn.stylefeng.roses.kernel.rule.pojo.request.BaseRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/** *uShow管理请求   */
@EqualsAndHashCode(callSuper = true)
@Data
public class UShowRequest extends BaseRequest {
    /** * uShowid  */
    @NotNull(message = "uShowid不能为空",groups = {edit.class,delete.class,detail.class})
    private Long ushowId;

    /** * uShow名称  */
    @NotNull(message = "uShow名称不能为空",groups = {add.class,edit.class})
    private String fileName;

    /** * uShow长度  */
    @NotNull(message = "uShow长度不能为空",groups = {add.class,edit.class})
    private Integer trainingTime;

    /** * uShow模板id  */
    @NotNull(message = "uShow模板不能为空",groups = {add.class,edit.class})
    private Integer templateId;

    /** * uShowbind1  */
    @NotNull(message = "bind1不能为空",groups = {add.class,edit.class})
    private String bind1;

    /** * uShowbind6  */
    @NotNull(message = "bind6不能为空",groups = {add.class,edit.class})
    private String bind6;

    /** * uShow audio  */
    @NotNull(message = "uShow audio不能为空",groups = {add.class,edit.class})
    private String audio;

    /** * uShow video  */
    @NotNull(message = "uShow video不能为空",groups = {add.class,edit.class})
    private String video;

    /** * uShow描述 */
    private String fileStr;
}
