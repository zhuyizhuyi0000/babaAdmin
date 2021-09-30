package cn.stylefeng.guns.modular.resource.service;

import cn.stylefeng.guns.modular.resource.entity.Doc;
import cn.stylefeng.guns.modular.resource.pojo.DocRequest;
import cn.stylefeng.roses.kernel.db.api.pojo.page.PageResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface DocService extends IService<Doc> {
    /** *添加文档   */
    void add(DocRequest docRequest);

    /** *删除文档   */
    void del(DocRequest docRequest);

    /** *编辑文档   */
    void edit(DocRequest docRequest);

    /** *查看详情   */
    Doc detail(DocRequest docRequest);

    /** *分页查询   */
    PageResult<Doc> findPage(DocRequest docRequest);

    /** *查询所有文档   */
    List<Doc> findList(DocRequest docRequest);
}
