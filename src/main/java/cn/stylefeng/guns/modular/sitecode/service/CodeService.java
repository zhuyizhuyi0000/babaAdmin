package cn.stylefeng.guns.modular.sitecode.service;


import cn.stylefeng.guns.modular.sitecode.entity.Code;
import cn.stylefeng.guns.modular.sitecode.pojo.CodeRequest;
import cn.stylefeng.roses.kernel.db.api.pojo.page.PageResult;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface CodeService extends IService<Code> {
    /** *添加code   */
    Long add(CodeRequest codeRequest);

    /** *删除code   */
    Long del(CodeRequest codeRequest);

    /** *编辑code   */
    Long edit(CodeRequest codeRequest);

    /** *查看详情   */
    Code detail(CodeRequest codeRequest);

    /** *分页查询   */
    PageResult<Code> findPage(CodeRequest codeRequest);

    /** *查询所有code   */
    List<Code> findList(CodeRequest codeRequest);

    /** *通过站点查所有code集合   */
    List<Code> findCodeList(String site);

}
