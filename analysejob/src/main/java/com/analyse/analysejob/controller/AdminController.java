package com.analyse.analysejob.controller;

import com.analyse.analysejob.entity.Job;
import com.analyse.analysejob.entity.User;
import com.analyse.analysejob.service.JobService;
import com.analyse.analysejob.service.UserService;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("admin")
public class AdminController {

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
        return "admin/login";
    }

    //验证登录
    @PostMapping("/validateLogin")
    public String validateLogin(User user, HttpServletRequest request, RedirectAttributes redirectAttributes) {
        //验证用户名密码
        User validUser = userService.validateUser(user);
        if ((validUser != null) && validUser.getRole().equals("管理员")) {
            HttpSession session = request.getSession();
            session.setAttribute("userMessage", validUser);
            return "redirect:/admin/index";

        } else {
            redirectAttributes.addFlashAttribute("errorMessage", true);
            return "redirect:/admin/login";
        }
    }

    //首页
    @RequestMapping("/index")
    public String index(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session.getAttribute("userMessage") == null) {
            return "redirect:/admin/login";
        } else {
            return "admin/index";
        }
    }

    //用户管理界面
    @RequestMapping("/manageUser")
    public String manageUser(@RequestParam(value = "keyword", required = false) String keyword, @RequestParam(value = "page", required = false) Integer currentPage, Model model) {
        User user = new User();
        //获取关键字
        if (keyword == null) {
            keyword = "";
        } else {
            user.setUsername(keyword);
            user.setRealname(keyword);
            user.setProfession(keyword);
        }
        //当前页面
        if (currentPage == null) {
            currentPage = 1;
        }
        PageRequest request = PageRequest.of(currentPage - 1, 10);
        Page<User> users = userService.getUsersByKeyword(user, request);
        int totalPages = users.getTotalPages();
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
        model.addAttribute("users", users.getContent());
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("currentPage", currentPage);
        model.addAttribute("hasPrev", hasPrev);
        model.addAttribute("hasNext", hasNext);
        model.addAttribute("pages", pages);
        model.addAttribute("keyword", keyword);
        return "admin/manageUser";
    }

    //用户管理增加用户
    @PostMapping("/addUser")
    public String addUser(User user) {
        userService.addUser(user);
        return "redirect:/admin/manageUser";
    }

    //用户管理修改用户
    @RequestMapping("/updateUser")
    public String updateUser(@RequestParam("uid") Integer uid, User user) {
        user.setUid(uid);
        userService.updateUser(user);
        return "redirect:/admin/manageUser";
    }

    //用户管理删除用户
    @RequestMapping("/deleteUser")
    public String deleteUser(@RequestParam("uid") Integer uid) {
        userService.deleteUserById(uid);
        return "redirect:/admin/manageUser";
    }

    //用户管理批量删除用户
    @RequestMapping("/deleteUsers")
    public String deleteUsers(@RequestParam("uids") String uids) {
        userService.deleteUsersByIds(uids);
        return "redirect:/admin/manageUser";
    }

    //数据管理界面
    @RequestMapping("/manageData")
    public String manageData(@RequestParam(value = "keyword", required = false) String keyword, @RequestParam(value = "page", required = false) Integer currentPage, Model model) {
        Job job = new Job();
        //获取关键字
        if (keyword == null) {
            keyword = "";
        } else {
            job.setJob_name(keyword);
            job.setJob_city(keyword);
            job.setTags(keyword);
        }
        //当前页面默认为1
        if (currentPage == null) {
            currentPage = 1;
        }
        PageRequest request = PageRequest.of(currentPage - 1, 10);
        Page<Job> jobs = jobService.getJobsByKeyword(job, request);
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
        return "admin/manageData";
    }

    //数据管理修改数据
    @RequestMapping("/updateData")
    public String updateData(@RequestParam("jid") String jid, Job job) {
        job.setUrl_object_id(jid);
        jobService.updateJob(job);
        return "redirect:/admin/manageData";
    }

    //数据管理删除数据
    @RequestMapping("/deleteData")
    public String deleteData(@RequestParam("jid") String jid) {
        jobService.deleteJobById(jid);
        return "redirect:/admin/manageData";
    }

    //数据管理批量删除数据
    @RequestMapping("/deleteDatas")
    public String deleteDatas(@RequestParam("jids") String jids) {
        jobService.deleteJobsByIds(jids);
        return "redirect:/admin/manageData";
    }

    //退出系统
    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/admin/login";
    }

    //允许前端的string类型转存Date类型
    @InitBinder
    protected void init(HttpServletRequest request, ServletRequestDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }
}
