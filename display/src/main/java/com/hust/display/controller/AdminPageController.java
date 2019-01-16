package com.hust.display.controller;

import com.hust.display.entity.Job;
import com.hust.display.entity.User;
import com.hust.display.service.JobService;
import com.hust.display.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("admin")
public class AdminPageController {

    @Autowired
    UserService userService;
    @Autowired //annotation必须要有
    JobService jobService;

    @RequestMapping("/index")
    public String indexPage(){
        return "/admin/index";
    }

    //不分页
    @RequestMapping("/manageUser")
    public String userManagement(Model model){
        List<User> lists = userService.getAllUser();
        model.addAttribute("users",lists);
        return "/admin/manageUser";
    }

    @RequestMapping("/addUser")
    public String addUser(User user){
        userService.addUser(user);
        return "redirect:/admin/manageUser";
    }

    @RequestMapping("/delete/{userName}")
    public String deleteByName(@PathVariable("userName")String userName){
        userService.deleteByName(userName);
        return "redirect:/admin/manageUser";
    }

    @RequestMapping("/manageData")
    public String dataManagement(Model model){
        List<Job> lists = jobService.getAllJob();
        model.addAttribute("jobs",lists);
        return "/admin/manageData";
    }



    @RequestMapping("/login")
    public String login(){
        return "/admin/login";
    }

//    @RequestMapping("/calendar")
//    public String calendar(){
//        return "/admin/calendar";
//    }

}
