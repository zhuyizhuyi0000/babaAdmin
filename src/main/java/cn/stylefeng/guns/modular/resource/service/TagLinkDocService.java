package cn.stylefeng.guns.modular.resource.service;

import cn.stylefeng.guns.modular.resource.entity.TagLinkDoc;
import cn.stylefeng.guns.modular.resource.entity.TagDoc;
import cn.stylefeng.guns.modular.resource.pojo.DocRequest;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface TagLinkDocService extends IService<TagLinkDoc> {
    /** *添加文档关联关系   */
    void add(DocRequest videoRequest);

    /** *添加文档关联关系   */
    void add(List<Long> list,Long id);

    /** *删除文档关联关系   */
    void del(Long id,int num);

    /** *查询所有文档关联关系   */
    List<TagLinkDoc> findList(Long id,int num);

    /** *查询所有文档关联关系，只返回tag list   */
    List<Long> findTagList(Long id,int num);

    /** *查询所有文档关联关系，返回map的list   */
    List<TagDoc> findTagMapList(Long id, int num);

    /** *查询所有文档关联关系的集合，避免查多次，只返回tag list   */
    List<TagLinkDoc> findAllTagList(List<Long> list,int num);
}
