package cn.stylefeng.guns.modular.resource.service;

import cn.stylefeng.guns.modular.resource.entity.TagLinkVideo;
import cn.stylefeng.guns.modular.resource.entity.TagVideo;
import cn.stylefeng.guns.modular.resource.pojo.VideoRequest;
import cn.stylefeng.roses.kernel.db.api.pojo.page.PageResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

public interface TagLinkVideoService extends IService<TagLinkVideo> {
    /** *添加视频关联关系   */
    void add(VideoRequest videoRequest);

    /** *添加视频关联关系   */
    void add(List<Long> list,Long id);

    /** *删除视频关联关系   */
    void del(Long id,int num);

    /** *查询所有视频关联关系   */
    List<TagLinkVideo> findList(Long id,int num);

    /** *查询所有视频关联关系，只返回tag list   */
    List<Long> findTagList(Long id,int num);

    /** *查询所有视频关联关系，返回map的list   */
    List<TagVideo> findTagMapList(Long id, int num);

    /** *查询所有视频关联关系的集合，避免查多次，只返回tag list   */
    List<TagLinkVideo> findAllTagList(List<Long> list,int num);
}
