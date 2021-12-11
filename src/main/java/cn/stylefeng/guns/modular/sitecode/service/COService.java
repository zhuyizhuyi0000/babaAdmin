package cn.stylefeng.guns.modular.sitecode.service;

import cn.stylefeng.guns.modular.sitecode.entity.Lang;

import java.util.List;
import java.util.Map;

public interface COService {
    /** *   */
    Map<String,List<Lang>> findPageDetail(String mode);

}
