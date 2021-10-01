package cn.stylefeng.guns.modular.resource.service;

import cn.stylefeng.guns.modular.resource.entity.TagVideo;
import cn.stylefeng.guns.modular.resource.pojo.TagVideoRequest;
import cn.stylefeng.roses.kernel.db.api.pojo.page.PageResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

public interface TagVideoService extends IService<TagVideo> {
    /** *添加视频标签   */
    void add(TagVideoRequest tagvideoRequest);

    /** *删除视频标签   */
    Long del(TagVideoRequest tagvideoRequest);

    /** *编辑视频标签   */
    void edit(TagVideoRequest tagvideoRequest);

    /** *查看详情   */
    TagVideo detail(TagVideoRequest tagvideoRequest);

    /** *分页查询   */
    PageResult<TagVideo> findPage(TagVideoRequest tagvideoRequest);

    /** *查询所有视频标签   */
    List<TagVideo> findList(TagVideoRequest tagvideoRequest);

    /** *查询名称   */
    String getName(Long id);

    //返回id+标签名字
//    Map<Long,String> findAllTagNameList(List<Long> list);
    List<TagVideo> findAllTagNameList(List<Long> list);
}
