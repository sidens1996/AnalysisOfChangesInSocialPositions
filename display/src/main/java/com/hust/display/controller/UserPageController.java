package com.hust.display.controller;

import com.hust.display.entity.User;
import com.hust.display.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("admin")
public class UserPageController {

    @Autowired
    UserService userService;

    @RequestMapping("/index")
    public String indexPage(){
        return "/admin/index";
    }

    @RequestMapping("/manageUser")
    public String userManagement(Model model){
        List<User> lists = userService.getAllUser();
        model.addAttribute("users",lists);
        return "/admin/manageUser";
    }

    @RequestMapping("/manageData")
    public String dataManagement(){
        return "/admin/manageData";
    }

}
