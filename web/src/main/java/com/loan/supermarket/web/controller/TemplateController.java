package com.loan.supermarket.web.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.loan.supermarket.mapper.JsonObject;
import com.loan.supermarket.mapper.User;
import com.loan.supermarket.service.UserServiece;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.List;

@Controller
public class TemplateController {
    @Autowired
    UserServiece userServiece;

//    @RequestMapping("/index")
//    public String index(Model model) {
//        List<User> list = userServiece.getUser();
//        model.addAttribute("users", list);
//        return "index.htmlbac";
//    }

//    @RequestMapping("/login")
//    public String login1() {
//        return "login.html";
//    }

    @PostMapping("/user_login")
    @ResponseBody
    public JsonObject<String> login(String username, String password, HttpServletRequest request) {
        JsonObject<String> obj = new JsonObject<String>();
        HttpSession session = request.getSession();
        List<User> userList = userServiece.queryUserByName(username);
        for (User userTmp : userList) {
            if (!StringUtils.isEmpty(username) && !StringUtils.isEmpty(password) && userTmp.getPassword().equals(password)) {
                obj.setCode("0");
                User user = new User();
                user.setName(username);
                System.out.println(username);
                session.setAttribute("loginUser", user);
                return obj;
            }
        }
        obj.setCode("1");
        obj.setMessage("用户名或密码错误");
        return obj;
    }

    @Autowired
    private DefaultKaptcha captchaProducer;

    /**
     * 获取验证码 的 请求路径
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @throws Exception
     */
    @RequestMapping("/defaultKaptcha")
    public void defaultKaptcha(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) throws Exception {
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
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @return
     */
    @RequestMapping("/imgvrifyControllerDefaultKaptcha")
    public ModelAndView imgvrifyControllerDefaultKaptcha(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        ModelAndView andView = new ModelAndView();
        String captchaId = (String) httpServletRequest.getSession().getAttribute("vrifyCode");
        String parameter = httpServletRequest.getParameter("vrifyCode");
        System.out.println("Session  vrifyCode " + captchaId + " form vrifyCode " + parameter);

        if (!captchaId.equals(parameter)) {
            andView.addObject("info", "错误的验证码");
            andView.setViewName("index.htmlbac");
        } else {
            andView.addObject("info", "登录成功");
            andView.setViewName("success");
        }
        return andView;
    }
}