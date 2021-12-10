package cn.stylefeng.guns.modular.test;

import cn.stylefeng.guns.modular.common.httpUtils.HttpClientBase;
import io.leopard.javahost.JavaHost;
import io.restassured.response.Response;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static io.restassured.RestAssured.given;

public class testStart {
    public final static String PRO = "https://www.umu.cn/passport/ajax/account/login";
    public final static String PROVIDEO = "https://www.umu.cn/ajax/resource/getresourcelist?is_recycle=0&search_keyword=&page_rows=15&order_by=create_time&is_desc=1&media_type=videoweike";
    public final static String PRODOC = "https://www.umu.cn/ajax/resource/getresourcelist?is_recycle=0&search_keyword=&page_rows=15&order_by=create_time&is_desc=1&media_type=docweike";
    public final static String PROCOM = "https://www.umu.com/passport/ajax/account/login";
    public final static String PROVIDEOCOM = "https://www.umu.com/ajax/resource/getresourcelist?is_recycle=0&search_keyword=&page_rows=15&order_by=create_time&is_desc=1&media_type=videoweike";
    public final static String PRODOCCOM = "https://www.umu.com/ajax/resource/getresourcelist?is_recycle=0&search_keyword=&page_rows=15&order_by=create_time&is_desc=1&media_type=docweike";
    public final static String USERNAME1 = "ytest1234@qq.com";
    public final static String PASSWD = "qweqwe";

    public static void main(String args[]) throws IOException {

        loadDns();
        JavaHost.printAllVirtualDns();

        Response response1 = given().cookies(new HttpClientBase().doPost(USERNAME1,PASSWD,PRO)).when().log().all().get("https://www.umu.cn/course/index#/groups");


        //        System.out.println(response1.getCookies());
//        System.out.println(response1.getHeaders());
        System.out.println(response1.asString());
        System.out.println("========================");
        System.out.println(response1.getHeaders().toString());
        System.out.println("========================");
        System.out.println(response1.getCookies().toString());
        System.out.println("========================");
        System.out.println(response1.getContentType());
        System.out.println("========================");
        System.out.println(response1.getSessionId());
        System.out.println("========================");
        System.out.println(response1.getBody().toString());
        System.out.println("========================");
        System.out.println(response1.getDetailedCookies().toString());
        System.out.println("========================");
        System.out.println(response1.getStatusCode());
        System.out.println("========================");


        String test1 = response1.asString();
//        System.out.println(test1);
        Pattern p = Pattern.compile("\\s*|\t|\r|\n");
        Matcher m = p.matcher(test1);
        String s = m.replaceAll("");
        System.out.println(s);
        System.out.println("========================");
        String regex = "window.version=\"(.*?)\"";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(s);
        while (matcher.find()) {
            System.out.println(matcher.group(1));
            }

    }

    public static void loadDns() throws IOException {
        Resource resource = new ClassPathResource("/vdns-pre.properties");
        Properties props = PropertiesLoaderUtils.loadProperties(resource);
        Resource resource2 = new ClassPathResource("/vdns-online.properties");
        Properties props2 = PropertiesLoaderUtils.loadProperties(resource2);
        System.out.println(props);
        Map props1 = new HashMap();
        props1.put("www.umu.cn","81.70.88.72");
//        JavaHost.updateVirtualDns(props);
        JavaHost.updateVirtualDns("www.umu.cn","81.70.88.72");
        JavaHost.updateVirtualDns("m.umu.cn","81.70.88.72");
        JavaHost.printAllVirtualDns();

        JavaHost.updateVirtualDns("www.umu.cn","152.136.248.130");
        JavaHost.updateVirtualDns("m.umu.cn","152.136.248.130");
        JavaHost.printAllVirtualDns();

    }
}
