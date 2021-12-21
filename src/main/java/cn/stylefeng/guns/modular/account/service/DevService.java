package cn.stylefeng.guns.modular.account.service;

import cn.stylefeng.guns.modular.account.entity.Dev;
import cn.stylefeng.guns.modular.account.pojo.DevRequest;
import cn.stylefeng.roses.kernel.db.api.pojo.page.PageResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface DevService extends IService<Dev> {
    /** *添加dev   */
    void add(DevRequest devRequest);

    /** *删除dev   */
    void del(DevRequest devRequest);

    /** *编辑dev   */
    void edit(DevRequest devRequest);

    /** *查看详情   */
    Dev detail(DevRequest devRequest);

    /** *分页查询   */
    PageResult<Dev> findPage(DevRequest devRequest);

    /** *查询所有dev   */
    List<Dev> findList(DevRequest devRequest);
}
