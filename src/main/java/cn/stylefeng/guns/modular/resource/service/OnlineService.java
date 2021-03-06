package cn.stylefeng.guns.modular.resource.service;

import cn.stylefeng.guns.modular.resource.entity.Doc;
import cn.stylefeng.guns.modular.resource.entity.Video;
import cn.stylefeng.guns.modular.resource.pojo.OnlineRequest;

import java.util.List;

public interface OnlineService  {
    /** *通过用户名密码查资源   */
    List<Video> findPageDetail(String userName,String passWord,Integer pageNum,Integer mode,Integer site);

    /** *通过用户名密码查看单个数据   */
    Video findPageOneDetail(String userName,String passWord,Integer pageNum,Integer num,Integer mode,Integer site);

    /** *通过用户名密码查资源   */
    List<Doc> findPageDetailDoc(String userName,String passWord,Integer pageNum,Integer mode,Integer site);

    /** *通过用户名密码查看单个数据   */
    Doc findPageOneDetailDoc(String userName, String passWord, Integer pageNum, Integer num, Integer mode, Integer site);
}
