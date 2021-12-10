package cn.stylefeng.guns.modular.sitecode.service.impl;

import cn.stylefeng.guns.modular.common.httpUtils.HttpClientBase;
import cn.stylefeng.guns.modular.sitecode.entity.Code;
import cn.stylefeng.guns.modular.sitecode.entity.Lang;
import cn.stylefeng.guns.modular.sitecode.service.CNService;
import cn.stylefeng.guns.modular.sitecode.service.CodeService;
import io.leopard.javahost.JavaHost;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.text.StringEscapeUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** * code业务实现层   */
@Slf4j
@Service
public class CNServiceImpl implements CNService {
    public final static String PRO = "https://www.umu.cn/passport/ajax/account/login";
    public final static String PROVIDEO = "https://www.umu.cn/ajax/resource/getresourcelist?is_recycle=0&search_keyword=&page_rows=15&order_by=create_time&is_desc=1&media_type=videoweike";
    public final static String PRODOC = "https://www.umu.cn/ajax/resource/getresourcelist?is_recycle=0&search_keyword=&page_rows=15&order_by=create_time&is_desc=1&media_type=docweike";
    public final static String PROCOM = "https://www.umu.com/passport/ajax/account/login";
    public final static String PROVIDEOCOM = "https://www.umu.com/ajax/resource/getresourcelist?is_recycle=0&search_keyword=&page_rows=15&order_by=create_time&is_desc=1&media_type=videoweike";
    public final static String PRODOCCOM = "https://www.umu.com/ajax/resource/getresourcelist?is_recycle=0&search_keyword=&page_rows=15&order_by=create_time&is_desc=1&media_type=docweike";
    public final static String USERNAME1 = "ytestcode001@umu.com";
    public final static String USERNAME2 = "ytestcode002@umu.com";
    public final static String USERNAME3 = "ytestcode006@umu.com";
    public final static String USERNAME4 = "ytestcode007@umu.com";
    public final static String PASSWD = "qweqwe";
    public final static String PROCHANGE = "https://www.umu.cn/api/user/updatesetup";
    public final static String ONLINEIP="152.136.248.130";
    public final static String PREIP="81.70.88.72";

    @Resource
    private CodeService codeService;

    @Override
    public Map<String,List<Lang>> findPageDetail(String mode){
        Map<String,List<Lang>> langListMap= new LinkedHashMap<>();
        String site = "0"; //CN
        List<Code> codeList = codeService.findCodeList(site);
        if(codeList.size() == 0){
            return langListMap;
        }
        List<Lang> findpageList = getResList(codeList,mode);

        for(Lang lang : findpageList){
            List<Lang> tmp = new ArrayList<>();
            if(!langListMap.containsKey(lang.getName())){
                tmp.add(lang);
                langListMap.put(lang.getName(),tmp);
            }else {
                tmp = langListMap.get(lang.getName());
                tmp.add(lang);
                langListMap.put(lang.getName(),tmp);
            }
        }

        return langListMap;
    }


    public List<Lang> getResList(List<Code> codeList,String mode){
        List<Lang> ListCode = new ArrayList<>();

//        log.info("===========");
//        log.info(codeList.toString());
//        log.info("===========");

        //pro
        JavaHost.updateVirtualDns("www.umu.cn",ONLINEIP);
        JavaHost.updateVirtualDns("m.umu.cn",ONLINEIP);
        //cn-cn pro
        RequestSpecification requestSpecification;
        requestSpecification = getRequestSpecification(USERNAME1,PASSWD,PRO);

        //重置语言
        Map<String, String> par = new LinkedHashMap<>();
        par.put("option_type", "7");
        par.put("option_value", "zh-cn");
        RestAssured.given(requestSpecification).params(par).when().log().all().post(PROCHANGE);

        for (Code code : codeList) {
            Lang lang = new Lang();
            lang.setName(code.getName());
            lang.setSite("CN");
            lang.setLanguage("cn");
            lang.setEnv("PRO");
            String url = code.getUrl();
            String siteCode = RestAssured.given(requestSpecification).when().log().all().get(url).asString();
            String checkCode = getCode(siteCode, code.getLogic());
            lang.setValue(checkCode);
            ListCode.add(lang);
        }

        //cn-en pro
        RequestSpecification requestSpecification1;
        requestSpecification1 = getRequestSpecification(USERNAME2,PASSWD,PRO);

        //重置语言
        Map<String, String> par1 = new LinkedHashMap<>();
        par1.put("option_type", "7");
        par1.put("option_value", "en-us");
        RestAssured.given(requestSpecification1).params(par1).when().log().all().post(PROCHANGE);

        for (Code code : codeList) {
            Lang lang = new Lang();
            lang.setName(code.getName());
            lang.setSite("CN");
            lang.setLanguage("en");
            lang.setEnv("PRO");
            String url = code.getUrl();
            String siteCode = RestAssured.given(requestSpecification1).when().log().all().get(url).asString();
            String checkCode = getCode(siteCode, code.getLogic());
            lang.setValue(checkCode);
            ListCode.add(lang);
        }

        //cn-cn canary
        RequestSpecification requestSpecification3;
        requestSpecification3 = getRequestSpecification(USERNAME3,PASSWD,PRO);

        //重置语言
        Map<String, String> par3 = new LinkedHashMap<>();
        par3.put("option_type", "7");
        par3.put("option_value", "zh-cn");
        RestAssured.given(requestSpecification).params(par3).when().log().all().post(PROCHANGE);

        for (Code code : codeList) {
            Lang lang = new Lang();
            lang.setName(code.getName());
            lang.setSite("CN");
            lang.setLanguage("cn");
            lang.setEnv("CANARY");
            String url = code.getUrl();
            String siteCode = RestAssured.given(requestSpecification3).when().log().all().get(url).asString();
            String checkCode = getCode(siteCode, code.getLogic());
            lang.setValue(checkCode);
            ListCode.add(lang);
        }

        //cn-en canary
        RequestSpecification requestSpecification4;
        requestSpecification4 = getRequestSpecification(USERNAME4,PASSWD,PRO);

        //重置语言
        Map<String, String> par4 = new LinkedHashMap<>();
        par4.put("option_type", "7");
        par4.put("option_value", "en-us");
        RestAssured.given(requestSpecification1).params(par4).when().log().all().post(PROCHANGE);

        for (Code code : codeList) {
            Lang lang = new Lang();
            lang.setName(code.getName());
            lang.setSite("CN");
            lang.setLanguage("en");
            lang.setEnv("CANARY");
            String url = code.getUrl();
            String siteCode = RestAssured.given(requestSpecification4).when().log().all().get(url).asString();
            String checkCode = getCode(siteCode, code.getLogic());
            lang.setValue(checkCode);
            ListCode.add(lang);
        }

        //pre
        JavaHost.updateVirtualDns("www.umu.cn",PREIP);
        JavaHost.updateVirtualDns("m.umu.cn",PREIP);
        //cn-cn pre
        for (Code code : codeList) {
            Lang lang = new Lang();
            lang.setName(code.getName());
            lang.setSite("CN");
            lang.setLanguage("cn");
            lang.setEnv("PRE");
            String url = code.getUrl();
            String siteCode = RestAssured.given(requestSpecification).when().log().all().get(url).asString();
            String checkCode = getCode(siteCode, code.getLogic());
            lang.setValue(checkCode);
            ListCode.add(lang);
        }

        //cn-en pre
        for (Code code : codeList) {
            Lang lang = new Lang();
            lang.setName(code.getName());
            lang.setSite("CN");
            lang.setLanguage("en");
            lang.setEnv("PRE");
            String url = code.getUrl();
            String siteCode = RestAssured.given(requestSpecification1).when().log().all().get(url).asString();
            String checkCode = getCode(siteCode, code.getLogic());
            lang.setValue(checkCode);
            ListCode.add(lang);
        }


        return ListCode;
    }

    public String getCode(String code,String regex){
        String codeResult = "";
        //通用处理，所有 空
        Pattern p = Pattern.compile("\\s*|\t|\r|\n");
        Matcher m = p.matcher(code);
        String s = m.replaceAll("");

//        regex = "window.version=\"(.*?)\"";
        //库里 window.version=&quot;(.*?)&quot;  需要html转义符 转译
        regex = StringEscapeUtils.unescapeHtml4(regex.trim());
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(s);
        if (matcher.find()) {
            codeResult=matcher.group(1);
        }
        return codeResult;
    }

    private RequestSpecification getRequestSpecification(String userName,String passWord,String url){
        RequestSpecBuilder builder = new RequestSpecBuilder();

        builder.addCookies(new HttpClientBase().doPost(userName,passWord,url));

//        builder.addParams();
        return builder.build();
    }
}
