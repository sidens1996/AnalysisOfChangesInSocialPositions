package com.analyse.analysejob.controller;

import com.analyse.analysejob.entity.Job;
import com.analyse.analysejob.entity.User;
import com.analyse.analysejob.service.JobService;
import com.analyse.analysejob.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Controller
public class UserController {
    @Resource
    UserService userService;
    @Resource
    JobService jobService;
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
    public String result(@RequestParam(value = "keyword") String keyword, @RequestParam(value = "page", required = false) Integer currentPage, Job job, Model model)
    {
        job.setJob_name(keyword);
        //当前页面
        if (currentPage == null) {
            currentPage = 1;
        }
        PageRequest request = PageRequest.of(currentPage - 1, 10);
        Page<Job> jobs = jobService.findByCondition(job,request);
        int totalPages = jobs.getTotalPages();
        boolean hasPrev = true;
        boolean hasNext = true;
        //判断是否有上一页或者下一页
        if (currentPage == 1) {
            hasPrev = false;
        }
        if (currentPage == totalPages) {
            hasNext = false;
        }
        List<Integer> pages = new ArrayList();
        int min = Math.max(currentPage - 2, 1);
        int max = Math.min(currentPage + 2, totalPages) + 1;
        for (int i = min; i < max; i++) {
            pages.add(i);
        }
        if (min - 1 >= 2) {
            pages.add(0, 0);
        }
        if (totalPages - max >= 2) {
            pages.add(0);
        }
        model.addAttribute("jobs", jobs.getContent());
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("hasPrev", hasPrev);
        model.addAttribute("hasNext", hasNext);
        model.addAttribute("pages", pages);
        model.addAttribute("keyword", keyword);
        model.addAttribute("job", job);
        return "result";
    }

}
