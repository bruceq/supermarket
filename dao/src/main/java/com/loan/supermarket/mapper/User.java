package com.loan.supermarket.mapper;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class User {
    private Integer uid;
    private String username;
    private Set<Role> roles = new HashSet<>();
    private Long id;
    private String name;
    private Integer age;
    private String telephone;
    private String password;
    private Date registerTime;
    private Integer popedom;

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public Integer getPopedom() {
        return popedom;
    }

    public void setPopedom(Integer popedom) {
        this.popedom = popedom;
    }
}
