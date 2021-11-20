package cn.stylefeng.guns.modular.resource.service;

import cn.stylefeng.guns.modular.resource.entity.Doc;
import cn.stylefeng.guns.modular.resource.pojo.OnlineRequest;
import cn.stylefeng.guns.modular.resource.pojo.DocRequest;
import cn.stylefeng.roses.kernel.db.api.pojo.page.PageResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface DocService extends IService<Doc> {
    /** *添加文档   */
    Long add(DocRequest docRequest);

    /** *删除文档   */
    Long del(DocRequest docRequest);

    /** *编辑文档   */
    Long edit(DocRequest docRequest);

    /** *查看详情   */
    Doc detail(DocRequest docRequest);

    /** *分页查询   */
    PageResult<Doc> findPage(DocRequest docRequest);

    /** *查询所有文档   */
    List<Doc> findList(DocRequest docRequest);

    /** *通过用户名密码查资源   */
    List<Doc> findPageDetail(OnlineRequest onlineRequest);

    /** *通过用户名密码只查1个资源   */
    Doc findPageOneDetail(OnlineRequest onlineRequest);
}
