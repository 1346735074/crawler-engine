package com.crawler.core;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.HttpClient;

import java.util.HashMap;
import java.util.Map;

/**
 * cookies工具类
 *
 * @author only
 */
@Slf4j
public class CookieUtil {

    /**
     * 获取cookies
     *
     * @param client
     * @return
     */
    public static Map<String, String> getCookisMap(HttpClient client) {
        Map<String, String> cookieMap = new HashMap<String, String>();
        Cookie[] cookies = client.getState().getCookies();
        for (Cookie cookie : cookies) {
            cookieMap.put(cookie.getName(), cookie.getValue());
            log.info("[[[" + cookie.getName() + ":" + cookie.getValue() + "]]]");
        }
        return cookieMap;
    }


    /**
     * Cookie获取
     *
     * @param client
     * @return
     */
    public static Cookie[] getCookis(HttpClient client) {
        Cookie[] cookies = client.getState().getCookies();
        if (cookies.length == 0) {
            log.info("None");
        } else {
            for (int i = 0; i < cookies.length; i++) {
                log.info(cookies[i].toString());
            }
            log.info("Cookie获取成功");
        }
        return cookies;
    }

    /**
     * Cookie获取
     *
     * @param client
     * @return
     */
    public static StringBuffer getCookisStringBuffer(HttpClient client) {
        // 获得登陆后的 Cookie
        Cookie[] cookies = client.getState().getCookies();
        StringBuffer tmpcookies = new StringBuffer();
        for (Cookie c : cookies) {
            tmpcookies.append(c.toString() + ";");
            log.info("cookies = " + c.toString());
        }
        return tmpcookies;
    }

    /**
     * cookies转换
     *
     * @param cookies
     * @return
     */
    public static String[] cookisConvert(Cookie[] cookies) {
        String[] result = null;
        if (cookies.length == 0) {
            log.info("Cookies is not Exists ");
        } else {
            for (int i = 0; i < cookies.length; i++) {
                log.info(cookies[i].toString());
                result = cookies[i].toString().split("=");
            }
            log.info("Cookie转换成功");
        }
        return result;
    }

}
