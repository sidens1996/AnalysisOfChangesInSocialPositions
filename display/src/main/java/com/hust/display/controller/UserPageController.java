package com.hust.display.controller;

import com.hust.display.entity.Job;
import com.hust.display.entity.User;
import com.hust.display.repository.UserRepository;
import com.hust.display.service.JobService;
import com.hust.display.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("admin")
public class UserPageController {

    @Autowired
    UserService userService;
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
