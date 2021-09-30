package cn.stylefeng.guns.modular.resource.service;

import cn.stylefeng.guns.modular.resource.entity.UShow;
import cn.stylefeng.guns.modular.resource.pojo.UShowRequest;
import cn.stylefeng.roses.kernel.db.api.pojo.page.PageResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface UShowService extends IService<UShow> {
    /** *添加uShow   */
    void add(UShowRequest uShowRequest);

    /** *删除uShow   */
    void del(UShowRequest uShowRequest);

    /** *编辑uShow   */
    void edit(UShowRequest uShowRequest);

    /** *查看详情   */
    UShow detail(UShowRequest uShowRequest);

    /** *分页查询   */
    PageResult<UShow> findPage(UShowRequest uShowRequest);

    /** *查询所有uShow   */
    List<UShow> findList(UShowRequest uShowRequest);
}
