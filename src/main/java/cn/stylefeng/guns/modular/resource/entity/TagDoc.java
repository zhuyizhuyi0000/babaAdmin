package cn.stylefeng.guns.modular.resource.entity;

import cn.stylefeng.roses.kernel.db.api.pojo.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 文档标签管理
 */
@TableName("tag_doc")
@EqualsAndHashCode(callSuper = true)
@Data
public class TagDoc extends BaseEntity {
    /** * 文档标签id */
    @TableId("id")
    private Long id;

    /** * 文档标签名称 */
    @TableField("name")
    private String name;

    /** * 文档标签描述  */
    @TableField("description")
    private String description;

}
