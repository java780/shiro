package com.shiro.auttle3.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.apache.shiro.util.ThreadContext;
import org.junit.After;



public abstract class BaseTest {

    @After
    public void tearDown(){

        ThreadContext.unbindSubject();
    }


    protected void login(String configFile,String username,String password){

        Factory<SecurityManager> iniSecurityManagerFactory = new IniSecurityManagerFactory(configFile);

        SecurityManager securityManager = iniSecurityManagerFactory.getInstance();

        SecurityUtils.setSecurityManager(securityManager);

        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken(username,password);

        subject.login(token);
    }

    public Subject subject(){
        return SecurityUtils.getSubject();
    }
}
