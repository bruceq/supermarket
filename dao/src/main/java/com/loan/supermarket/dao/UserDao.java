package com.loan.supermarket.dao;

import com.loan.supermarket.mapper.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {

    int addUser(User user);

    int deleteUserById(Long id);

    int updateUserById(User user);

    User queryUserById(Long id);

    List<User> queryUserList();

    List<User> queryUserByName(String name);

    User findUserByUsername(@Param("username") String username);
}
