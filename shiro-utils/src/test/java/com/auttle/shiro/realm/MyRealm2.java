package com.auttle.shiro.realm;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.shiro.authc.*;
import org.apache.shiro.realm.Realm;

public class MyRealm2 implements Realm {

    public String getName() {
        return "MyRealm2";
    }

    public boolean supports(AuthenticationToken token) {
        return token instanceof UsernamePasswordToken;
    }

    public AuthenticationInfo getAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {


        String username = (String) token.getPrincipal();

        String password = new String((char[]) token.getCredentials());

        if(!"wang".equals(username)){
            throw new UnknownAccountException();
        }

        if(!"123".equals(password)){
            throw new IncorrectCredentialsException();
        }

        return new SimpleAuthenticationInfo(username,password,getName());
    }
}
