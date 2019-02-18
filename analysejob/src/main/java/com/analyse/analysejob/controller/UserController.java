package com.analyse.analysejob.controller;

import com.analyse.analysejob.entity.User;
import com.analyse.analysejob.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;


@Controller
public class UserController {
    @Resource
    UserService userService;

    //登录界面
    @RequestMapping("/login")
    public String login(Model model) {
        Map mp = model.asMap();
        if (mp.get("errorMessage") != null) {
            model.addAttribute("errorMessage", "用户名或密码错误!");
        }
        return "login";
    }

    //验证登录
    @PostMapping("/validateLogin")
    public String validateLogin(User user, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        //验证用户名和密码
        User validUser = userService.validateUser(user);
        //判断当前用户存在且为"普通用户"
        if ((validUser != null) && validUser.getRole().equals("用户")) {
            HttpSession session = request.getSession();
            session.setAttribute("userMessage", validUser);
            return "redirect:/index";
        } else {
            //用户不存在或非普通用户无法登陆
            redirectAttributes.addFlashAttribute("errorMessage", true);
            return "redirect:/login";
        }
    }

    //首页
    @RequestMapping("/index")
    public String index(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session.getAttribute("userMessage") == null) {
            return "redirect:/login";
        } else {
            return "index";
        }
    }


    //注册页面
    @RequestMapping("/register")
    public String toRegister() {
        return "register";
    }

    //注册用户
    @RequestMapping("registerNewUser")
    public String registerUser(User user) {
        user.setRole("用户");//设置当前注册用户角色为“普通用户”
        userService.addUser(user);
        return "redirect:/login";
    }

    //推荐界面
    @RequestMapping("/recommend")
    public String toRecommand() {
        return "recommend";
    }

    //预测界面
    @RequestMapping("/forecast")
    public String toForcast() {
        return "forecast";
    }
}
