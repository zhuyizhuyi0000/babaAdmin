package cn.stylefeng.guns.modular.resource.service.impl;

import cn.stylefeng.guns.modular.resource.common.httpUtils.HttpClientBase;
import cn.stylefeng.guns.modular.resource.entity.Video;
import cn.stylefeng.guns.modular.resource.service.OnlineService;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

/** * uShow管理业务实现层   */
@Slf4j
@Service
public class OnlineServiceImpl implements OnlineService {
    public final static String PRO = "https://www.umu.cn/passport/ajax/account/login";
    public final static String PROVIDEO = "https://www.umu.cn/ajax/resource/getresourcelist?is_recycle=0&search_keyword=&page_rows=15&order_by=create_time&is_desc=1&media_type=videoweike";
    public final static String PROCOM = "https://www.umu.com/passport/ajax/account/login";
    public final static String PROVIDEOCOM = "https://www.umu.com/ajax/resource/getresourcelist?is_recycle=0&search_keyword=&page_rows=15&order_by=create_time&is_desc=1&media_type=videoweike";
    //    public final static String USERNAME = "ytest1234@qq.com";
//    public final static String PASSWD = "qweqwe";

    @Override
    public Video findPageOneDetail(String userName,String passWord,Integer pageNum,Integer num,Integer mode,Integer site){
        Video video = new Video();
        if(userName == null || userName.equals("") || userName.length()==0 || passWord == null || passWord.equals("") || passWord.length()==0){
            return video;
        }
        if(null == pageNum){pageNum = 1;}
        if(null == mode || mode != 2){ mode = 1 ;}
        List<Map<String,String>> resList = getResList(userName,passWord,pageNum,mode,site);
        if(resList == null || resList.size() == 0){
            return video;
        }
        if(null == num ||num >= resList.size()){
            num = 0;
        }

        video.setFileName(resList.get(num).get("file_name"));
        String fileSi = resList.get(num).get("file_size");
        Long fs = null;
        if (fileSi != null) {
            fs = Long.valueOf(fileSi);
        }
        video.setFileSize(fs);

        String fileDur = resList.get(num).get("file_duration");
        Integer fd = null;
        if (fileDur != null) {
            fd = Integer.valueOf(fileDur);
        }
        video.setFileDuration(fd);
        video.setUrl(resList.get(num).get("url"));
        video.setExt(resList.get(num).get("ext"));
        video.setTranscodingUrl(resList.get(num).get("transcoding_url"));
        video.setThumbInfo(resList.get(num).get("thumb_info"));

        return video;
    }

    @Override
    public List<Video> findPageDetail(String userName, String passWord, Integer pageNum, Integer mode,Integer site){
        List<Video> allVideoList = new ArrayList<>();

        if(userName == null || userName.equals("") || userName.length()==0 || passWord == null || passWord.equals("") || passWord.length()==0){
            return allVideoList;
        }
        if(null == pageNum){pageNum = 1;}
        if(null == mode || mode != 2){ mode = 1 ;}
        List<Map<String,String>> resList = getResList(userName,passWord,pageNum,mode,site);
//        JSONObject jsonObject = JSONObject.parseObject(resList.get(0).toString());

        if(resList == null || resList.size() == 0){
            return allVideoList;
        }

        for (Map<String, String> stringStringMap : resList) {
            Video video = new Video();
            video.setFileName(stringStringMap.get("file_name"));

            String fileSi = stringStringMap.get("file_size");
            Long fs = null;
            if (fileSi != null) {
                fs = Long.valueOf(fileSi);
            }
            video.setFileSize(fs);

            String fileDur = stringStringMap.get("file_duration");
            Integer fd = null;
            if (fileDur != null) {
                fd = Integer.valueOf(fileDur);
            }
            video.setFileDuration(fd);

            video.setUrl(stringStringMap.get("url"));
            video.setExt(stringStringMap.get("ext"));
            video.setTranscodingUrl(stringStringMap.get("transcoding_url"));
            video.setThumbInfo(stringStringMap.get("thumb_info"));
            allVideoList.add(video);
        }

//        Video video = new Video();
//        video.setVidId(123L);
//        video.setExt(String.valueOf(resList.size()));
////        video.setFileName(jsonObject.getString("url"));
//        video.setFileName(resList.get(0).toString());
//        video.setExt(resList.get(0).get("url").toString());
////        video.setFileName(getResList(USERNAME,PASSWD,1,1).toString());
//        allVideoList.add(video);
        return allVideoList;
    }

    public List<Map<String,String>> getResList(String userName, String passWord, Integer pageNum, Integer mode,Integer site){
//        if(mode==2){ url}
        String url;
        RequestSpecification requestSpecification;
        if(site == 2){
            url = PROVIDEOCOM + "&page=" + pageNum;
            requestSpecification = getRequestSpecification(userName,passWord,PROCOM);
        }else {
            url = PROVIDEO + "&page=" + pageNum;
            requestSpecification = getRequestSpecification(userName,passWord,PRO);
        }
        List<Map<String,String>> jsonRes =RestAssured.given(requestSpecification).when().log().all().get(url)
                .then().
                extract().
                path("data.list");;
        return jsonRes;
    }

    private RequestSpecification getRequestSpecification(String userName,String passWord,String url){
        RequestSpecBuilder builder = new RequestSpecBuilder();

        builder.addCookies(new HttpClientBase().doPost(userName,passWord,url));

//        builder.addParams();
        return builder.build();
    }


//    public String getCookie(String userName, String passWord, Integer pageNum, Integer mode){
//        Map<String, String> par = new LinkedHashMap<>();
//        par.put("username", USERNAME);
//        par.put("passwd", PASSWD);
//
//        String videoUrl = PROVIDEO + "&page=" + pageNum;
//
//        Response response = RestAssured.given()
//                .header("Cookie","JSESSID")
//                .params(par)
//                .when().log().all().post(PRO);
////        return response.asString();
//        log.info(response.getCookies().toString());
//        log.info(response.getCookie("Cookie"));
//
//        Response response1 = given().cookies(response.getCookies()).when().log().all().get(videoUrl);
//
//        return response1.asString();
//    }



}
