package com.analyse.analysejob.controller;

import com.analyse.analysejob.entity.User;
import com.analyse.analysejob.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("admin")
public class AdminController {

    @Resource
    UserService userService;

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
        if (userService.validateUser(user) == null) {
            redirectAttributes.addFlashAttribute("errorMessage", true);
            return "redirect:/admin/login";
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("userMessage", validUser);
            return "redirect:/admin/index";
        }
    }

    //首页
    @RequestMapping("/index")
    public String index(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session.getAttribute("userMessage") == null) {
            return "admin/login";
        } else {
            return "admin/index";
        }
    }

    //用户管理界面
    @RequestMapping("/manageUser")
    public String manageUser(@RequestParam(value = "keyword", required = false) String keyword,@RequestParam(value = "page", required = false) Integer currentPage,Model model) {
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
        Page<User> users = userService.getUsersByKeyword(user,request);
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
    public String manageData(@RequestParam(value = "keyword", required = false) String keyword,@RequestParam(value = "page", required = false) Integer currentPage,Model model) {
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
        Page<User> users = userService.getUsersByKeyword(user,request);
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
        return "admin/manageData";
    }
}
