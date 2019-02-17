package com.loan.supermarket.web.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
    public PageInfo<User> getUsers() {
        int pageNum = 1;
        int pageSize = 2;
        PageHelper.startPage(pageNum, pageSize);
        List<User> userList = userServiece.getUser();
        PageInfo<User> pageInfo = new PageInfo<>(userList);
        return pageInfo;
    }
}
