package cn.stylefeng.guns.modular.resource.service;

import cn.stylefeng.guns.modular.resource.entity.Video;
import cn.stylefeng.guns.modular.resource.pojo.VideoRequest;
import cn.stylefeng.roses.kernel.db.api.pojo.page.PageResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface VideoService extends IService<Video> {
    /** *添加视频   */
    Long add(VideoRequest videoRequest);

    /** *删除视频   */
    Long del(VideoRequest videoRequest);

    /** *编辑视频   */
    Long edit(VideoRequest videoRequest);

    /** *查看详情   */
    Video detail(VideoRequest videoRequest);

    /** *分页查询   */
    PageResult<Video> findPage(VideoRequest videoRequest);

    /** *查询所有视频   */
    List<Video> findList(VideoRequest videoRequest);
}
