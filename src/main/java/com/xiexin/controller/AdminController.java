package com.xiexin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/page")
public class AdminController {
    @RequestMapping("/adminlogin")
    public String adminlogin(){
        return "adminlogin";
    }
}
