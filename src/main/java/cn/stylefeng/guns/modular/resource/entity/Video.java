package cn.stylefeng.guns.modular.resource.entity;

import cn.stylefeng.roses.kernel.db.api.pojo.entity.BaseEntity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.List;

/**
 * 视频管理
 */
@TableName("resource_video")
@EqualsAndHashCode(callSuper = true)
@Data
public class Video extends BaseEntity {
    /** * 视频id */
    @TableId("vid_id")
    private Long vidId;

    /** * 视频名称 */
    @TableField("file_name")
    private String fileName;

    /** * 视频大小 */
    @TableField("file_size")
    private Long fileSize;

    /** * 视频长度  */
    @TableField("file_duration")
    private Integer fileDuration;

    /** * 视频链接  */
    @TableField("url")
    private String url;

    /** * 视频后缀  */
    @TableField("ext")
    private String ext;

    /** * 视频转码链接  */
    @TableField("transcoding_url")
    private String transcodingUrl;

    /** * 视频封面图  */
    @TableField("thumb_info")
    private String thumbInfo;

    //不与数据库关联
    @TableField(exist = false)
    private List<Long> videoTag;

    public List<Long> getVideoTag(){
        return videoTag;
    }
    public void setVideoTag(List<Long> videoTag){
        this.videoTag = videoTag;
    }
}
