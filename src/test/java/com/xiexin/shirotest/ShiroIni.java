package com.xiexin.shirotest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.junit.jupiter.api.Test;

public class ShiroIni {
    //shiro 3大概念：subject（用户请求，主体），security Manager（shiro 的管理类  安全管理者） realms（数据库）
    //realms 分为：ini realm， jdbc realm ，自定义的real --- 常用自定义（mybatis）
    @Test
    public void test01(){
        //1、realms   是 subject 提供的登录，底层 还是 security Manager
        IniRealm iniRealm = new IniRealm("classpath:shiro.ini");
        //2、security Manager  依赖 IniRealm（数据库） 通过 set 注入进去
        DefaultSecurityManager sm = new DefaultSecurityManager();
        sm.setRealm(iniRealm);
        //以上步骤是指的是 把realms注入到了 sm当中，即 他们2个联系在了一起，下面就剩下 subject了
        //subject 不是 new 出来的，因为 subject 是一个实打实的对象，原本就有的
        //只需要 用shiro 的类做一个接待就行了    SecurityUtils：安全管理单元 依赖  security Manager  可以直接得到subject 对象，subject 功能是 登录
        SecurityUtils.setSecurityManager(sm);  //接管 sm
        Subject subject = SecurityUtils.getSubject();
        //就可以使用 subject了，直接让subject 做个登录   Authentication 认证   Token：就是 账户和密码进行加密后的字符串
        //拟定虚拟的 账户和密码
        String userName = "xiexin111";
        String userPwd = "123";
        //在这里 利用 shiro 把 userName 和 userPwd 变为一个 token
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(userName, userPwd);
        //System.out.println("顾客登录的时候把账户名和密码 加工后的 token = " + usernamePasswordToken.getPrincipal());  //前端输入的 token
        System.out.println("顾客登录的时候把账户名和密码 加工后的 token = " + usernamePasswordToken);

        UsernamePasswordToken dbToken = new UsernamePasswordToken("xinxin", "123");
        //System.out.println("数据库中 把账户名和密码 加工后的 token = " + dbToken.getPrincipal());  //getPrincipal  得到主体
        System.out.println("数据库中 把账户名和密码 加工后的 token = " + dbToken);
        //注意：这个登录是 shiro 提供的，我们自己不用写登录，shiro 已经接管了
        //IncorrectCredentialsException  密码错误
        //UnknownAccountException  账户错误
        try {
            subject.login(usernamePasswordToken);
            System.out.println("登陆成功");
        }catch (UnknownAccountException e){
            System.out.println("账号错误");
        }catch (IncorrectCredentialsException e){
            System.out.println("密码错误");
        }


    }
}
