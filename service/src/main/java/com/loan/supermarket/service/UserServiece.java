package com.loan.supermarket.service;

import com.loan.supermarket.dao.UserDao;
import com.loan.supermarket.mapper.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiece {
    @Autowired
    private UserDao userDao;

    public List<User> getUser(){
        return userDao.queryUserList();
    }

    public List<User> queryUserByName(String name){
        return userDao.queryUserByName(name);
    }

}
