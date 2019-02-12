package com.loan.supermarket.web.controller;

import com.loan.supermarket.mapper.JsonObject;
import com.loan.supermarket.mapper.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;

@Controller
public class TemplateController {

    @RequestMapping("/index")
    public String index(Model model) {
        User user = new User();
        user.setName("张三");
        user.setId(1L);
        user.setTelephone("123456789");
        user.setPassword("@#$%^&*()");
        user.setRegisterTime(new Date());
        user.setPopedom(1);
        User user1 = new User();
        user1.setName("李四");
        user1.setId(2L);
        user1.setTelephone("2222222");
        user1.setPassword("最近是南风天");
        user1.setRegisterTime(new Date());
        user1.setPopedom(1);
        User user2 = new User();
        user2.setName("王五");
        user2.setId(3L);
        user2.setTelephone("33333333333");
        user2.setPassword("妖姬");
        user2.setRegisterTime(new Date());
        user2.setPopedom(1);
        User user3 = new User();
        user3.setName("赵六");
        user3.setId(4L);
        user3.setTelephone("33333333333");
        user3.setPassword("过河卒");
        user3.setRegisterTime(new Date());
        user3.setPopedom(0);
        ArrayList<User> list = new ArrayList<User>();
        list.add(user);
        list.add(user1);
        list.add(user2);
        list.add(user3);
        model.addAttribute("users", list);

        return "index";
    }

    @RequestMapping("/login")
    public String login1() {
        return "/login";
    }

    @PostMapping("/user_login")
    @ResponseBody
    public JsonObject<String> login(String username, String password, HttpServletRequest request) {
        JsonObject<String> obj = new JsonObject<String>();
        HttpSession session = request.getSession();
        if (!StringUtils.isEmpty(username) && !StringUtils.isEmpty(password)) {
            obj.setCode("0");
            User user = new User();
            user.setName(username);
            session.setAttribute("loginUser", user);
            return obj;
        }
        obj.setCode("1");
        obj.setMessage("用户名或密码错误");
        return obj;
    }

}