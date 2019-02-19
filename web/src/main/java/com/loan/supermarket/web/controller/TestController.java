package com.loan.supermarket.web.controller;

import com.loan.supermarket.mapper.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class TestController
{

    @RequestMapping("login")
    public String login()
    {
        return "login";
    }

    @RequestMapping("/index")
    public String index()
    {
        return "index";
    }

    @RequestMapping("/logout")
    public String logout()
    {
        // 先验证主体
        Subject subject = SecurityUtils.getSubject();
        if (subject != null)
        {
            subject.logout();
        }
        return "login";
    }

    @RequestMapping("unauthorized")
    public String unauthorized()
    {
        return "unauthorized";
    }

    @RequestMapping("/admin")
    @ResponseBody
    public String admin()
    {
        return "admin success";
    }

    @RequestMapping("/edit")
    @ResponseBody
    public String edit()
    {
        return "edit success";
    }

    @RequestMapping("/loginUser")
    public String loginUser(@RequestParam("username") String username,
                            @RequestParam("password") String password,
                            HttpSession session)
    {
        // 初始化这个用户的token
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);

        // 获取事件的主体
        Subject subject = SecurityUtils.getSubject();
        try
        {
            // 尝试登录
            subject.login(token);

            // 获取用户的全部信息
            User user = (User) subject.getPrincipal();

            // 用于界面输出
            session.setAttribute("user", user);
            return "index";
        }
        catch (Exception e)
        {
            return "login";
        }
    }
}
