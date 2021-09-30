package cn.stylefeng.guns.modular.resource.entity;

import cn.stylefeng.roses.kernel.db.api.pojo.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 视频标签管理
 */
@TableName("tag_vid")
@EqualsAndHashCode(callSuper = true)
@Data
public class TagVideo extends BaseEntity {
    /** * 视频标签id */
    @TableId("id")
    private Long id;

    /** * 视频标签名称 */
    @TableField("name")
    private String name;

    /** * 视频标签描述  */
    @TableField("description")
    private String description;

}
