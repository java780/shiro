package com.shiro.auttle3.test;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.permission.WildcardPermission;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collection;

public class PermissionTest extends BaseTest {

    @Test
    public void testIsPermitted(){

        login("classpath:shiro-permission.ini","zhang","123");

        Assert.assertTrue(subject().isPermitted("user:create"));

        Assert.assertTrue(subject().isPermittedAll("user:update","user:delete"));

        Assert.assertFalse(subject().isPermitted("user:view"));
    }

    @Test(expected = UnauthorizedException.class)
    public void testCheckPermission(){

        login("classpath:shiro-permission.ini","zhang","123");

        subject().checkPermission("user:create");

        subject().checkPermissions("user:update","user:delete");

        subject().checkPermission("user:view");
    }

    @Test
    public void testWildcardPermission1(){

        login("classpath:shiro-permission.ini","li","123");

        subject().checkPermissions("system:user:update","system:user:delete");

        subject().checkPermissions("system:user:update,delete");
    }

    @Test
    public void testWildcardPermission2(){

        login("classpath:shiro-permission.ini","li","123");

        subject().checkPermissions("system:user:create,delete,user:view");

        subject().checkPermissions("system:user:*");

        subject().checkPermissions("system:user");
    }


    @Test
    public void testWildcardPermission3(){

        login("classpath:shiro-permission.ini","li","123");

        subject().checkPermissions("user:view");

        subject().checkPermissions("system:user:view");
    }

    @Test
    public void testWildcardPermission4(){

        login("classpath:shiro-permission.ini","li","123");

        subject().checkPermissions("user:view:1");

        subject().checkPermissions("user:delete,update:1");

        subject().checkPermissions("user:delete:1","user:update:1");

        subject().checkPermissions("user:delete:1","user:update:1","user:view:1");

        subject().checkPermissions("user:auth:1","user:auth:2");
    }

    @Test
    public void testWildcardPermission5(){

        login("classpath:shiro-permission.ini","li","123");

        subject().checkPermissions("menu:view:1");

        subject().checkPermissions("organization");

        subject().checkPermissions("organization:view");

        subject().checkPermissions("organization:view:1");
    }

    @Test
    public void testWildcardPermission6(){

        login("classpath:shiro-permission.ini","li","123");

        subject().checkPermissions("menu:view:1");

        subject().checkPermission(new WildcardPermission("menu:view:1"));
    }


}
