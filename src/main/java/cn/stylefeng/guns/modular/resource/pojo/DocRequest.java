package cn.stylefeng.guns.modular.resource.pojo;

import cn.stylefeng.guns.modular.resource.entity.TagDoc;
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
public class DocRequest extends BaseRequest {
    /** * 文档id  */
    @NotNull(message = "文档id不能为空",groups = {edit.class,delete.class,detail.class})
    private Long docId;

    /** * 文档名称  */
    @NotNull(message = "文档名称不能为空",groups = {add.class,edit.class})
    private String fileName;

    /** * 文档链接  */
    @NotNull(message = "文档链接不能为空",groups = {add.class,edit.class})
    private String url;

    /** * 文档大小 */
    private Long fileSize;

    /** * 文档后缀  */
    private String ext;

    /** * 文档转码链接  */
    private String pdfUrl;

    /** * 文档封面图  */
    private String thumbInfo;

    /** * 文档关联标签  */
    private List<Long> docTag;

    /** * 文档关联标签list 带name了  */
    private List<TagDoc> docTagList;
}
