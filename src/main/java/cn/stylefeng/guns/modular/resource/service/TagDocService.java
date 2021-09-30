package cn.stylefeng.guns.modular.resource.service;

import cn.stylefeng.guns.modular.resource.entity.TagDoc;
import cn.stylefeng.guns.modular.resource.pojo.TagDocRequest;
import cn.stylefeng.roses.kernel.db.api.pojo.page.PageResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface TagDocService extends IService<TagDoc> {
    /** *添加文档标签   */
    void add(TagDocRequest tagdocRequest);

    /** *删除文档标签   */
    void del(TagDocRequest tagdocRequest);

    /** *编辑文档标签   */
    void edit(TagDocRequest tagdocRequest);

    /** *查看详情   */
    TagDoc detail(TagDocRequest tagdocRequest);

    /** *分页查询   */
    PageResult<TagDoc> findPage(TagDocRequest tagdocRequest);

    /** *查询所有文档标签   */
    List<TagDoc> findList(TagDocRequest tagdocRequest);
}
