package com.auttle.shiro;


import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.apache.shiro.util.ThreadContext;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

public class AuthenticatorTest {

    @Test
    public void testAllSuccessfulStrategyWithSuccess(){
        login("classpath:shiro-authenticator-all-success.ini");

        Subject subject = SecurityUtils.getSubject();

        PrincipalCollection principalCollection = subject.getPrincipals();

        Assert.assertEquals(2,principalCollection.asList().size());
    }

    @Test
    public void testAllSuccessfulStrategyWithFail(){

        login("classpath:shiro-authenticator-all-fail.ini");

        Subject subject = SecurityUtils.getSubject();

        PrincipalCollection principalCollection = subject.getPrincipals();

        Assert.assertEquals(2,principalCollection.asList().size());
    }


    private void login(String config){

        Factory<SecurityManager> iniSecurityManagerFactory = new IniSecurityManagerFactory(config);

        SecurityManager securityManager = iniSecurityManagerFactory.getInstance();

        SecurityUtils.setSecurityManager(securityManager);

        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken("zhang","123");

        subject.login(token);
    }

    @After
    public void tearDown(){

        ThreadContext.unbindSubject();
    }
}
