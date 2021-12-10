package cn.stylefeng.guns.modular.sitecode.entity;

import cn.stylefeng.roses.kernel.db.api.pojo.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * site code管理
 */
@TableName("sitecode_url")
@EqualsAndHashCode(callSuper = true)
@Data
public class Code extends BaseEntity {
    /** * codeid */
    @TableId("id")
    private Long id;

    /** * code名称 */
    @TableField("name")
    private String name;

    /** * code site */
    @TableField("site")
    private String site;

    /** * code链接  */
    @TableField("url")
    private String url;

    /** * code描述  */
    @TableField("description")
    private String description;

    /** * logic描述  */
    @TableField("logic")
    private String logic;

}
