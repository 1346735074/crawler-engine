package com.crawler.core;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.Map;

/**
 * Jsoup 工具类
 *
 * @author only
 */
@Slf4j
public class JsoupUtil {

    /**
     * 获取指定页面
     *
     * @param url
     * @return
     */
    public static Document getHtmlDocument(String url) {
        try {
            Document doc = Jsoup.connect(url).get();
            log.info("获取成功:" + doc.title());
            return doc;
        } catch (IOException e) {
            log.error("页面获取异常 %s", e);
        }
        return null;
    }

    /**
     * 获取指定页面
     *
     * @param cookiesmap
     * @param url
     * @return
     */
    public static Document getHtmlDocument(Map<String, String> cookiesmap, String url) {
        try {
            Document doc = Jsoup.connect(url).cookies(cookiesmap).get();
            log.info("获取成功:" + doc.title());
            return doc;
        } catch (SocketTimeoutException e) {
            log.info("页面获取异常开始重新获取");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ee) {
                ee.printStackTrace();
            }
            getHtmlDocument(cookiesmap, url);
            e.printStackTrace();
        } catch (IOException e) {
            log.error("页面获取异常 %s", e);
        }
        return null;
    }


    /**
     * 获取指定页面Json
     *
     * @param cookiesmap
     * @param url
     * @return
     */
    public static Document getHtmlJson(Map<String, String> cookiesmap, String url) {
        try {
            Document doc = Jsoup.connect(url).cookies(cookiesmap).ignoreContentType(true).get();
            log.info("获取成功:" + doc.title());
            return doc;
        } catch (IOException e) {
            log.error("页面获取异常 %s", e);
        }
        return null;
    }

    /**
     * postJson
     *
     * @param cookiesMap
     * @param url
     * @return
     */
    public static Document postHtmlJson(Map<String, String> cookiesMap, String url, String data) {
        try {
            Document doc = Jsoup.connect(url + data).cookies(cookiesMap).ignoreContentType(true).post();
            log.info("获取成功:" + doc.title());
            return doc;
        } catch (IOException e) {
            log.error("页面获取异常 %s", e);
        }
        return null;
    }
}
