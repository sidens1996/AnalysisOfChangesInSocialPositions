package com.hust.display.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class demoView {

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String index() {
        return "login.html";
    }
}
