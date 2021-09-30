package cn.stylefeng.guns.modular.resource.entity;

import cn.stylefeng.roses.kernel.db.api.pojo.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * uShow管理
 */
@TableName("resource_ushow")
@EqualsAndHashCode(callSuper = true)
@Data
public class UShow extends BaseEntity {
    /** * uShowid */
    @TableId("ushow_id")
    private Long ushowId;

    /** * uShow名称 */
    @TableField("file_name")
    private String fileName;

    /** * uShow描述 */
    @TableField("file_str")
    private String fileStr;

    /** * uShow长度  */
    @TableField("training_time")
    private Integer trainingTime;

    /** * uShow模板id  */
    @TableField("template_id")
    private Integer templateId;

    /** * uShowbind1  */
    @TableField("bind1")
    private String bind1;

    /** * uShowbind6  */
    @TableField("bind6")
    private String bind6;

    /** * uShowaudio  */
    @TableField("audio")
    private String audio;

    /** * uShowvideo  */
    @TableField("video")
    private String video;
}
