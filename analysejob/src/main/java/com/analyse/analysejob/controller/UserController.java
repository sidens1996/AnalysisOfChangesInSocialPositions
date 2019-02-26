package com.analyse.analysejob.controller;

import com.analyse.analysejob.entity.*;
import com.analyse.analysejob.service.DataService;
import com.analyse.analysejob.service.JobService;
import com.analyse.analysejob.service.UserService;
import com.analyse.analysejob.util.AIModule;
import com.analyse.analysejob.util.JobTrendData;
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
    public String recommend(Model model) {
        List<Tags> tagsList = dataService.getAllTags();
        model.addAttribute(tagsList);
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
    public JobTrendData loadForecast() {
        AIModule ai=new AIModule();
        Date date = new java.sql.Date(System.currentTimeMillis());
        date = ai.dateShift(date, -15);
        JobTrendData jobTrendData=ai.getJobTrend(12, date);
        return jobTrendData;
    }

    //预测十大职位
    @RequestMapping("/forecasting")
    @ResponseBody
    public JobTrendData forecasting(@RequestParam(value = "forecastdate") Date date) {
        AIModule ai=new AIModule();
        JobTrendData jobTrendData=ai.getJobTrend(12, date);
        return jobTrendData;
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
//        String[] testTagStr=new String[] {"算法"};
//        String[] testWordStr=new String[] {"员工福利"};
        String[] testTagStr = tags.split(",");
        TotalData total=ai.recommend(testTagStr);
        return total;
//        TotalData datas = new TotalData();
//        List<CityData> cityData1s = new ArrayList<>();
//        List<DegreeData> degreeDatas = new ArrayList<>();
//        //jobname
//        datas.setJobname("java工程师");
//        //data1
//        String [] citys = {"宁波", "深圳 ", "广州 ", "杭州", "成都", "武汉", "南京", "厦门", "西安", "长沙", "郑州", "重庆", "合肥", "天津", "福州", "济南", "无锡", "青岛", "大连", "东莞"};
//        Integer [] salaryData = {15, 18, 19, 13, 10, 10, 8, 2, 10, 12, 15, 14, 14, 12, 13, 16, 18, 13, 15, 5};
//        for (int i = 0; i < 20; i++) {
//            CityData cityData = new CityData();
//            cityData.setName(citys[i]);
//            cityData.setValue(salaryData[i] * 10);
//            cityData1s.add(cityData);
//        }
//        datas.setCityData(cityData1s);
//        //data2
//        String [] degrees = {"大专", "本科", "硕士", "博士", "其他"};
//        Integer [] das = {230, 300, 160, 80, 50};
//        for (int i = 0; i < 5; i++) {
//            DegreeData degreeData = new DegreeData();
//            degreeData.setName(degrees[i]);
//            degreeData.setValue(das[i]);
//            degreeDatas.add(degreeData);
//        }
//        datas.setDegreeData(degreeDatas);
//        //data3
////        String [] citys = {"宁波", "深圳", "广州", "杭州", "成都", "武汉", "南京", "厦门", "西安", "长沙", "郑州", "重庆", "合肥", "天津", "福州", "济南", "无锡", "青岛", "大连", "东莞"};
//        datas.setDataAxis(citys);
////        Integer [] salaryData = {15, 18, 19, 13, 12, 10, 8, 7, 10, 12, 15, 14, 14, 12, 13, 16, 18, 13, 15, 5};
//        datas.setSalarydata(salaryData);
//        Map maps = new HashMap<>();
//        maps.put("a",1);maps.put("b",2);maps.put("c",3);maps.put("d",4);maps.put("e",5);maps.put("f",6);maps.put("g",7);
//        datas.setKeywords(maps);
//        return datas;
    }
}
