package cn.stylefeng.guns.modular.account.entity;

import cn.stylefeng.roses.kernel.db.api.pojo.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * pd account
 */
@TableName("account_pd")
@EqualsAndHashCode(callSuper = true)
@Data
public class Pd extends BaseEntity {
    /** * id */
    @TableId("id")
    private Long id;

    /** * name */
    @TableField("name")
    private String name;

    /** * env */
    @TableField("env")
    private String env;

    /** * loginname */
    @TableField("loginname")
    private String loginName;

    /** * password */
    @TableField("password")
    private String password;

    /** * 描述  */
    @TableField("description")
    private String description;

}
