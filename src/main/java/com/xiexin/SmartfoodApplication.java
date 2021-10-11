package com.xiexin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//springboot 项目为了简化ssm项目而存在的
//ssm 项目配置比较繁琐，比如 需要配置 Tomcat，需要 有很多个 xml去配置 第三方依赖
//而 springboot 简化成，该内置的就内置了，多个xml配置成为 一个properties/yml 文件
//说白了 还是ssm框架，只不过 写起来简单了
@SpringBootApplication  //springboot 应用注解，标记 本项目是 springboot项目，必须有这个注解
@MapperScan("com.xiexin.dao")  //包扫描器
public class SmartfoodApplication {
    //main方法：项目一启动就会执行该方法
    public static void main(String[] args) {
        //静态调用 SpringApplication 应用，参数为 本项目的 启动类
        System.out.println(" springboot 项目 启动啦");
        SpringApplication.run(SmartfoodApplication.class, args);
    }

}
