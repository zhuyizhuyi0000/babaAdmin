package cn.stylefeng.guns.modular.resource.pojo;

import cn.stylefeng.guns.modular.resource.entity.TagVideo;
import cn.stylefeng.roses.kernel.rule.pojo.request.BaseRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.util.List;

/** *通过账号密码传递管理请求   */
@EqualsAndHashCode(callSuper = true)
@Data
public class OnlineRequest extends BaseRequest {
    /** * 用户名userName  */
    @NotNull(message = "用户名不能为空")
    private String userName;

    /** * 密码passWord  */
    @NotNull(message = "密码不能为空")
    private String passWord;

    /** * 页码数  */
    private Integer pageNum;

    /** * 个数  */
    private Integer num;

    /** * 模式 1=视频 2=文档 3=ushow */
    private Integer mode;

    /** * 站点 1=cn 2=com  */
    private Integer site;
}
