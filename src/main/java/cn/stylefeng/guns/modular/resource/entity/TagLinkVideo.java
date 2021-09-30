package cn.stylefeng.guns.modular.resource.entity;

import cn.stylefeng.roses.kernel.db.api.pojo.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 视频关联Tag
 */
@TableName("video_tag")
@EqualsAndHashCode(callSuper = true)
@Data
public class TagLinkVideo extends BaseEntity {
    /** * id */
    @TableId("vtag_id")
    private Long vtagId;

    /** * tag id */
    @TableField("tag_id")
    private Long tagId;

    /** * resource id */
    @TableField("resource_id")
    private Long resourceId;

    public Long getResourceId(){
        return resourceId;
    }

    public Long getTagId(){
        return tagId;
    }
    public void setTagId(Long tagId){
        this.tagId = tagId;
    }

}
