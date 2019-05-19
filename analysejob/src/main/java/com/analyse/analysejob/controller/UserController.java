package com.analyse.analysejob.controller;

import com.analyse.analysejob.entity.*;
import com.analyse.analysejob.service.DataService;
import com.analyse.analysejob.service.JobService;
import com.analyse.analysejob.service.UserService;
import com.analyse.analysejob.util.AIModule;
import com.analyse.analysejob.util.JobTrendData;
import com.analyse.analysejob.util.TotalTrendData;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
public class UserController {
    @Resource
    UserService userService;
    @Resource
    JobService jobService;
    @Resource
    DataService dataService;

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
        if (validUser != null && validUser.getRole().equals("用户")) {
            HttpSession session = request.getSession();
            session.setAttribute("userMessage", validUser);
            return "redirect:/index";
        } else {
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
    public String recommend(Model model) {
//        List<Tags> tagsList = dataService.getAllTags();
//        model.addAttribute(tagsList);
        return "recommend";
    }

    //预测
    @RequestMapping("/forecast")
    public String forecast() {
        return "forecast";
    }

    //加载十大职位
    @RequestMapping("/loadForecast")
    @ResponseBody
    public TotalTrendData loadForecast() {
        AIModule ai=new AIModule();
        Date date = new java.sql.Date(System.currentTimeMillis());
        date = ai.dateShift(date, -15);
        int[] testArray=new int[]{1,2,3,4,5,13,15,16,18,10};
        TotalTrendData total=ai.getTotalTrendData(testArray,date);
        return total;
    }

    //预测十大职位
    @RequestMapping("/forecasting")
    @ResponseBody
    public TotalTrendData forecasting(@RequestParam(value = "forecastdate") Date date) {
        AIModule ai=new AIModule();
        int[] testArray=new int[]{1,2,3,4,5,13,15,16,18,10};
        TotalTrendData total=ai.getTotalTrendData(testArray,date);
        return total;
    }

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

    @RequestMapping("/recommendResult")
    @ResponseBody
    public TotalData recommendResult(@RequestParam(value = "tags", required = false) String tags) {
        AIModule ai=new AIModule();
        tags = tags.replace(" ", "+");
        String[] testTagStr = tags.split(",");
        TotalData total=ai.recommend(testTagStr);
        return total;
    }
}
