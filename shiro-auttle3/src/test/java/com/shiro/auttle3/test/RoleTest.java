package com.shiro.auttle3.test;

import org.apache.shiro.authz.UnauthorizedException;
import org.junit.Assert;
import org.junit.Test;


import java.util.Arrays;

public class RoleTest extends BaseTest {

    @Test
    public void testHasRole(){
        login("classpath:shiro-role.ini","zhang","123");

        Assert.assertTrue(subject().hasRole("role1"));

        Assert.assertTrue(subject().hasAllRoles(Arrays.asList("role1","role2")));

        boolean[] booleans = subject().hasRoles(Arrays.asList("role1", "role2", "role3"));

        Assert.assertEquals(true,booleans[0]);
        Assert.assertEquals(true,booleans[1]);
        Assert.assertEquals(false,booleans[2]);
    }

    @Test(expected = UnauthorizedException.class)
    public void testCheckRole(){

        login("classpath:shiro-role.ini","zhang","123");

        subject().checkRole("role1");

        subject().checkRoles("role1","role3");
    }
}
