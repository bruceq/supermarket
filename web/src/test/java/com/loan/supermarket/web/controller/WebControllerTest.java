package com.loan.supermarket.web.controller;

import com.loan.supermarket.service.UserServiece;
import com.loan.supermarket.web.Application;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class WebControllerTest {
    @Autowired
    private UserServiece userServiece;
    @Test
    public void say() {
    }

    @Test
    public void getUsers() {
        Assert.assertNotNull(userServiece.getUser());
    }
}