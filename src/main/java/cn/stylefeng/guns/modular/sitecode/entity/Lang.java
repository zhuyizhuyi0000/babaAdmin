package cn.stylefeng.guns.modular.sitecode.entity;

import cn.stylefeng.roses.kernel.db.api.pojo.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * site code管理
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Lang extends BaseEntity {

    private String name;

    private String site;

    private String language;

    private String value;

    private String env;

}
