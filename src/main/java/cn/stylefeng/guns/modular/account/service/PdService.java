package cn.stylefeng.guns.modular.account.service;

import cn.stylefeng.guns.modular.account.entity.Pd;
import cn.stylefeng.guns.modular.account.pojo.PdRequest;
import cn.stylefeng.roses.kernel.db.api.pojo.page.PageResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface PdService extends IService<Pd> {
    /** *添加pd   */
    void add(PdRequest pdRequest);

    /** *删除pd   */
    void del(PdRequest pdRequest);

    /** *编辑pd   */
    void edit(PdRequest pdRequest);

    /** *查看详情   */
    Pd detail(PdRequest pdRequest);

    /** *分页查询   */
    PageResult<Pd> findPage(PdRequest pdRequest);

    /** *查询所有pd   */
    List<Pd> findList(PdRequest pdRequest);
}
