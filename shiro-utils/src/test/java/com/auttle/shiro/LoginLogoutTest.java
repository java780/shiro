package com.auttle.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.Ini;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.apache.shiro.util.ThreadContext;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 */
public class LoginLogoutTest {

    @Test
    public void testHelloWorld(){
        //获取SecurityManager工厂，使用ini文件初始化工厂
        Factory<org.apache.shiro.mgt.SecurityManager> iniSecurityManagerFactory = new IniSecurityManagerFactory("classpath:shiro.ini");

        SecurityManager instance = iniSecurityManagerFactory.getInstance();

        SecurityUtils.setSecurityManager(instance);

        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("zhang","123");

        subject.login(token);

        Assert.assertEquals(true,subject.isAuthenticated());

        subject.logout();
    }

    @Test
    public void testCustomRealm(){
        Factory<SecurityManager> iniSecurityManagerFactory = new IniSecurityManagerFactory("classpath:shiro-realm.ini");

        SecurityManager instance = iniSecurityManagerFactory.getInstance();

        SecurityUtils.setSecurityManager(instance);

        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("zhang","123");

        subject.login(token);

        Assert.assertEquals(true,subject.isAuthenticated());

        subject.logout();
    }

    @Test
    public void testCustomMultiRealm(){

        Factory<SecurityManager> iniSecurityManagerFactory = new IniSecurityManagerFactory("classpath:shiro-multi-realm.ini");

        SecurityManager securityManager = iniSecurityManagerFactory.getInstance();

        SecurityUtils.setSecurityManager(securityManager);

        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("wang","123");

        subject.login(token);

        Assert.assertEquals(true,subject.isAuthenticated());

        subject.logout();
    }

    @Test
    public void testJDBCRealm(){

        Factory<SecurityManager> iniSecurityManagerFactory = new IniSecurityManagerFactory("classpath:shiro-jdbc-realm.ini");

        SecurityManager securityManager = iniSecurityManagerFactory.getInstance();

        SecurityUtils.setSecurityManager(securityManager);

        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("zhang","123");

        subject.login(token);

        Assert.assertEquals(true,subject.isAuthenticated());

        subject.logout();

    }


    @After
    public void tearDowm(){
        ThreadContext.unbindSubject();
    }

}
