package com.awen.utils;

import com.awen.pojo.Content;
import org.elasticsearch.common.recycler.Recycler;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Liu Awen Email:willowawen@gmail.com
 * @create 2020-04-12 18:45
 */
@Component
public class HtmlParseUtil {
//    public static void main(String[] args) throws IOException {
////        new HtmlParseUtil().parseJD("java").forEach(System.out::println);
//        //英文可以 中文不可以 要支持
//        new HtmlParseUtil().parseJD("码出高效").forEach(System.out::println);
//    }
    public List<Content> parseJD(String keyword) throws IOException {
        //获得请求 https://search.jd.com/Search?keyword=java
        //前提 需要联网 ， 获取ajax数据 ajax 不能获取到,ajax 要模拟浏览器才能获取到！
        String url = "https://search.jd.com/Search?keyword=" + keyword;
        //中文？ 中文在url后面拼上&enc=utf-8
        url = url + "&enc=utf-8";
        //解析网页。(Jsoup返回Document就是浏览器Document对象) 浏览器能干的都能干了 document
        Document document = Jsoup.parse(new URL(url), 30000);
        //所有你在JS中可以使用的方法，这里都能用！！！
        Element element = document.getElementById("J_goodsList");
        //div ul li 数据在li里
        //我们打印一下
//        System.out.println(element.html());
        //src空？ source-data-lazy-img  有？？？ 存到这里了
        //<img width="220" height="220" class="err-product" data-img="1"
        // source-data-lazy-img="//img11.360buyimg.com/n7/jfs/t1/57972/5/2455/276693/5d036b6cEbb183907/b8c1845431ba6753.jpg">

        //获取所有的li元素
        Elements elements = element.getElementsByTag("li");
        // 获取元素中的内容, 这里的el 就是每一个li标签了!
        // 封装个对象 来存储爬的数据
        ArrayList<Content> goodsList = new ArrayList<>();
        for (Element el : elements) {
            //获取第一个图片 src属性   图片呢   套路企业的  速度 为什么获取不到呢
            //关于这种图片特别多的网站  图片是延迟加载的 懒加载过程
            // 开始的一张小小的其他图片  网页渲染完 拿过来 让我们网页加载快 默认的图片
            // source-data-lazy-img   性能

            String img = el.getElementsByTag("img").eq(0).attr("src");
//            String img = el.getElementsByTag("img").eq(0).attr("source-data-lazy-img");

            String price = el.getElementsByClass("p-price").eq(0).text();
            String title = el.getElementsByClass("p-name").eq(0).text();
//            String el.getElementsByClass("p-price").eq(0).text();
//            System.out.println("=========================");
//            System.out.println(img);
//            System.out.println(price);
//            System.out.println(title);
            Content content = new Content();
            content.setImg(img);
            content.setPrice(price);
            content.setTitle(title);
            goodsList.add(content);
        }
        return goodsList;
    }

}
