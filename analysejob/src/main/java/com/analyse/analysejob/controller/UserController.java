package com.analyse.analysejob.controller;

import com.analyse.analysejob.entity.User;
import com.analyse.analysejob.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@Controller
public class UserController {
    @Resource
    UserService userService;

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/login")
    public String login() { return "login"; }

    @RequestMapping("/register")
    public String register() { return "register"; }
}
