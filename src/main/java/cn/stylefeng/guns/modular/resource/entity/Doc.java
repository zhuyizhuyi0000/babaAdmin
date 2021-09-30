package cn.stylefeng.guns.modular.resource.entity;

import cn.stylefeng.roses.kernel.db.api.pojo.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 文档管理
 */
@TableName("resource_document")
@EqualsAndHashCode(callSuper = true)
@Data
public class Doc extends BaseEntity {
    /** * 文档id */
    @TableId("doc_id")
    private Long docId;

    /** * 文档名称 */
    @TableField("file_name")
    private String fileName;

    /** * 文档大小 */
    @TableField("file_size")
    private Long fileSize;

    /** * 文档链接  */
    @TableField("url")
    private String url;

    /** * 文档后缀  */
    @TableField("ext")
    private String ext;

    /** * 文档转码链接  */
    @TableField("pdf_url")
    private String pdfUrl;

    /** * 文档封面图  */
    @TableField("thumb_info")
    private String thumbInfo;
}
