package cn.stylefeng.guns.modular.resource.service;

import cn.stylefeng.guns.modular.resource.entity.Video;
import cn.stylefeng.guns.modular.resource.pojo.OnlineRequest;

import java.util.List;

public interface OnlineService  {
    /** *通过用户名密码查资源   */
    List<Video> findPageDetail(String userName,String passWord,Integer pageNum,Integer mode);
}
