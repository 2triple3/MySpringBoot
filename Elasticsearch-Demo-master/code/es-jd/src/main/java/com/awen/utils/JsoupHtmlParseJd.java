package com.awen.utils;

import com.awen.pojo.Content;
import com.awen.pojo.Product;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Liu Awen Email:willowawen@gmail.com
 * @create 2020-04-13 1:59
 */
public class JsoupHtmlParseJd {
    public static void main(String[] args) throws IOException {
        new JsoupHtmlParseJd().parseJD("java").forEach(System.out::println);
    }

    public List<Product> parseJD(String keyword) throws IOException {
        //获得请求 eg:https://search.jd.com/Search?keyword=java
        String url = "https://search.jd.com/Search?keyword=" + keyword;
        //中文？ 中文在url后面拼上&enc=utf-8
        url = url + "&enc=utf-8";
        //解析网页。(Jsoup返回Document就是浏览器Document对象)
        Document document = Jsoup.parse(new URL(url), 30000);
        Element element = document.getElementById("J_goodsList");
        //分析京东网页可得 id为"J_goodsList"的div的ul的li 数据在li里

        //获取所有的li元素, 那才是真的数据
        Elements elements = element.getElementsByTag("li");
        // 获取元素中的内容, 这里的el 就是每一个li标签了!
        // 封装个对象 来存储爬的数据
        ArrayList<Product> goodsList = new ArrayList<>();
        for (Element el : elements) {
            //获取第一个图片 src属性   图片呢   为什么获取不到呢
            //原来是 source-data-lazy-img   性能
            //String img = el.getElementsByTag("img").eq(0).attr("src");
            String img = el.getElementsByTag("img").eq(0).attr("source-data-lazy-img");
            String price = el.getElementsByClass("p-price").eq(0).text();
            String title = el.getElementsByClass("p-name").eq(0).text();
            String shop = el.getElementsByClass("p-shop").eq(0).text();
//            System.out.println("=========================");
//            System.out.println("标题:" + title);
//            System.out.println("图片url:" + img);
//            System.out.println("店铺:" + shop);
//            System.out.println("价格:" + price);
            Product product = new Product();
            product.setImg(img);
            product.setPrice(price);
            product.setTitle(title);
            product.setShop(shop);
            goodsList.add(product);
        }
        return goodsList;
    }

}
