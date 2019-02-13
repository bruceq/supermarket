package com.loan.supermarket.web.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.loan.supermarket.mapper.JsonObject;
import com.loan.supermarket.mapper.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.util.StringUtils;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
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
        return "login";
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

    @Autowired
    private DefaultKaptcha captchaProducer;
    /**
     * 获取验证码 的 请求路径
     * @param httpServletRequest
     * @param httpServletResponse
     * @throws Exception
     */
    @RequestMapping("/defaultKaptcha")
    public void defaultKaptcha(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception{
        byte[] captchaChallengeAsJpeg = null;
        ByteArrayOutputStream jpegOutputStream = new ByteArrayOutputStream();
        try {
            //生产验证码字符串并保存到session中
            String createText = captchaProducer.createText();
            httpServletRequest.getSession().setAttribute("vrifyCode", createText);
            //使用生产的验证码字符串返回一个BufferedImage对象并转为byte写入到byte数组中
            BufferedImage challenge = captchaProducer.createImage(createText);
            ImageIO.write(challenge, "jpg", jpegOutputStream);
        } catch (IllegalArgumentException e) {
            httpServletResponse.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        //定义response输出类型为image/jpeg类型，使用response输出流输出图片的byte数组
        captchaChallengeAsJpeg = jpegOutputStream.toByteArray();
        httpServletResponse.setHeader("Cache-Control", "no-store");
        httpServletResponse.setHeader("Pragma", "no-cache");
        httpServletResponse.setDateHeader("Expires", 0);
        httpServletResponse.setContentType("image/jpeg");
        ServletOutputStream responseOutputStream =
                httpServletResponse.getOutputStream();
        responseOutputStream.write(captchaChallengeAsJpeg);
        responseOutputStream.flush();
        responseOutputStream.close();
    }


    /**
     * 验证的方法
     * @param httpServletRequest
     * @param httpServletResponse
     * @return
     */
    @RequestMapping("/imgvrifyControllerDefaultKaptcha")
    public ModelAndView imgvrifyControllerDefaultKaptcha(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse){
        ModelAndView andView = new ModelAndView();
        String captchaId = (String) httpServletRequest.getSession().getAttribute("vrifyCode");
        String parameter = httpServletRequest.getParameter("vrifyCode");
        System.out.println("Session  vrifyCode "+captchaId+" form vrifyCode "+parameter);

        if (!captchaId.equals(parameter)) {
            andView.addObject("info", "错误的验证码");
            andView.setViewName("index");
        } else {
            andView.addObject("info", "登录成功");
            andView.setViewName("success");
        }
        return andView;
    }
}