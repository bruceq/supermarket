package com.loan.supermarket.mapper;

import java.util.Date;

public class User {
    private Long id;
    private String name;
    private Integer age;
    private String telephone;
    private String password;
    private Date registerTime;
    private Integer popedom;

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
