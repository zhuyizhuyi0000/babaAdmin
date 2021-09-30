package cn.stylefeng.guns.modular.resource.pojo;

import cn.stylefeng.roses.kernel.rule.pojo.request.BaseRequest;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;
import lombok.EqualsAndHashCode;

/** *视频管理请求   */
@EqualsAndHashCode(callSuper = true)
@Data
public class VideoRequest extends BaseRequest {
    /** * 视频id  */
    @NotNull(message = "视频id不能为空",groups = {edit.class,delete.class,detail.class})
    private Long vidId;

    /** * 视频名称  */
    @NotNull(message = "视频名称不能为空",groups = {add.class,edit.class})
    private String fileName;

    /** * 视频链接  */
    @NotNull(message = "视频链接不能为空",groups = {add.class,edit.class})
    private String url;

    /** * 视频大小 */
    private Long fileSize;

    /** * 视频长度  */
    private Integer fileDuration;

    /** * 视频后缀  */
    private String ext;

    /** * 视频转码链接  */
    private String transcodingUrl;

    /** * 视频封面图  */
    private String thumbInfo;

    /** * 视频关联标签  */
    private List<Long> videoTag;
}
