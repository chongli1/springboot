package com.xiexin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
//热部署快捷键 ctrl + F9
@Controller
public class IndexController {

    @RequestMapping( {"/index","/"})   //映射多个 html页面
    public String index(){
        return "index";
    }

//    @RequestMapping("/")
//    public String index1(){
//        return "index";
//    }
}
