package com.auttle.shiro.authenticator.strategy;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.pam.AbstractAuthenticationStrategy;
import org.apache.shiro.realm.Realm;

import java.util.Collection;

public class OnlyOneAuthenticatorStrategy extends AbstractAuthenticationStrategy {

    public OnlyOneAuthenticatorStrategy() {
        super();
    }

    @Override
    public AuthenticationInfo beforeAllAttempts(Collection<? extends Realm> realms, AuthenticationToken token) throws AuthenticationException {
        return new SimpleAuthenticationInfo();
    }

    @Override
    public AuthenticationInfo beforeAttempt(Realm realm, AuthenticationToken token, AuthenticationInfo aggregate) throws AuthenticationException {
        return aggregate;
    }

    @Override
    public AuthenticationInfo afterAttempt(Realm realm, AuthenticationToken token, AuthenticationInfo singleRealmInfo, AuthenticationInfo aggregateInfo, Throwable t) throws AuthenticationException {

        AuthenticationInfo info;

        if(singleRealmInfo == null){
            info = aggregateInfo;
        }else {
            if(aggregateInfo == null){
                info = singleRealmInfo;
            }else{
                info = merge(singleRealmInfo,aggregateInfo);
                if (info.getPrincipals().getRealmNames().size() > 1){
                    System.out.println(info.getPrincipals().getRealmNames());
                    throw new AuthenticationException("Authentication token of type" + token.getClass());
                }
            }
        }

        return super.afterAttempt(realm, token, singleRealmInfo, aggregateInfo, t);
    }



    @Override
    public AuthenticationInfo afterAllAttempts(AuthenticationToken token, AuthenticationInfo aggregate) throws AuthenticationException {
        return aggregate;
    }
}
