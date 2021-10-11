package com.xiexin.shirotest;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.jupiter.api.Test;

/**
 * shiro 的加密 和 认证测试
 */
public class MyShiro {
    //shiro 有对明文密码 123456 有 加密的 功能，让 web的密码更安全
    //md5加密，简单，但是 不可逆，但是可以根据 加密后的密码进行 反推
    //更加的 安全，就需要加盐
    @Test
    public void testCmd5(){
        Md5Hash md5Hash = new Md5Hash("123456");
        System.out.println("md5Hash = " + md5Hash);

        //给密码加盐
        Md5Hash md5Hash1 = new Md5Hash("123456", "shiliang");   //shiliang  写什么都行
        System.out.println("md5Hash1 = " + md5Hash1);

        //给加盐后的密码 进行散列处理
        Md5Hash md5Hash2 = new Md5Hash("123456", "shiliang", 1024);
        System.out.println("md5Hash2 = " + md5Hash2);
    }
}
