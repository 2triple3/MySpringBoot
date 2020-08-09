package com.awen.controller;

import com.awen.service.impl.ContentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author Liu Awen Email:willowawen@gmail.com
 * @create 2020-04-13 0:18
 */
//请求编写
@RestController
public class ContentController {
    @Autowired
    private ContentServiceImpl contentService;
    //Restful风格
    @GetMapping("/parse/{keyword}")
    public Boolean parse(@PathVariable("keyword")  String keyword) throws Exception{
        return contentService.parseContent(keyword);
    }
    //要解析吗  为什么一直是java呢 我开始的  查询的时候加上?
    @GetMapping("/search/{keyword}/{pageNo}/{pageSize}")
    public List<Map<String,Object>> search(@PathVariable("keyword") String keyword,
                                           @PathVariable("pageNo") int pageNo,
                                           @PathVariable("pageSize") int pageSize) throws Exception{

        if (pageNo == 0){

        }
        //我来搞一波测试  是不是string的问题
//        pageNo = 1;
//        pageSize = 5;
//        return  contentService.searchPage(keyword, pageNo, pageSize);
        //高亮
        return  contentService.searchPageHighlight(keyword, pageNo, pageSize);
    }
}
