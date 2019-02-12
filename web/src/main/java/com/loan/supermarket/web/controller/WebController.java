package com.loan.supermarket.web.controller;

import com.loan.supermarket.mapper.User;
import com.loan.supermarket.service.UserServiece;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WebController {
    @Autowired
    private UserServiece userServiece;

    @RequestMapping(value = "/hello")
    public String say() {
        return "Hello World!!";
    }

    @RequestMapping(value = "/user")
    public List<User> getUsers() {
        return userServiece.getUser();
    }
}
