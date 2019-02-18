package com.analyse.analysejob.controller;

import com.analyse.analysejob.entity.User;
import com.analyse.analysejob.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
        //验证用户名密码
        User validUser = userService.validateUser(user);
        if (validUser == null) {
            redirectAttributes.addFlashAttribute("errorMessage", true);
            return "redirect:/login";
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("userMessage", validUser);
            return "redirect:/index";
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

    //退出系统
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }

    //注册界面
    @RequestMapping("/register")
    public String register() { return "register"; }

    //用户注册
    @RequestMapping("/registerUser")
    public String registerUser(User user,Model model) {
        user.setRole("用户");
        User newUser = userService.registerUser(user);
        if (newUser == null) {
            model.addAttribute("errorMessage", "用户名重复，注册失败!");
            return "register";
        } else {
            return "redirect:/login";
        }
    }

    //推荐
    @RequestMapping("/recommend")
    public String recommend() { return "recommend"; }

    //预测
    @RequestMapping("/forecast")
    public String forecast() { return "forecast"; }

    //结果
    @RequestMapping("/result")
    public String result() { return "result"; }

}
