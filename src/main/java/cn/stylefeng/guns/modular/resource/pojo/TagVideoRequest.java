package cn.stylefeng.guns.modular.resource.pojo;

import cn.stylefeng.roses.kernel.rule.pojo.request.BaseRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;

/** *视频标签管理请求   */
@EqualsAndHashCode(callSuper = true)
@Data
public class TagVideoRequest extends BaseRequest {
    /** * 视频标签id  */
    @NotNull(message = "视频标签id不能为空",groups = {edit.class,delete.class,detail.class})
    private Long id;

    /** * 视频标签名称  */
    @NotNull(message = "视频标签名称不能为空",groups = {add.class,edit.class})
    private String name;

    /** * 视频标签链接  */
    private String description;

}
