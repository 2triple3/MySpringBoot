package com.awen.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Liu Awen Email:willowawen@gmail.com
 * @create 2020-04-12 18:38
 */
@Controller
public class IndexController {
    @GetMapping({"/","/index"})
    public String index(){
        return "index";
    }
}
