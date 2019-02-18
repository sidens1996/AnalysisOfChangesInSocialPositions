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
        if (userService.validateUser(user) == null) {
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
//    @RequestMapping("/index")
//    public String index() {
//        return "index";
//    }
//
//    @RequestMapping("/login")
//    public String login() { return "login"; }

    @RequestMapping("/register")
    public String register() { return "register"; }
}
