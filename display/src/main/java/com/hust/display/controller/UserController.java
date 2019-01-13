package com.hust.display.controller;

import com.hust.display.entity.User;
import com.hust.display.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/add")
    private User addUser(User user){
        return userService.addUser(user);
    }

    @PostMapping("/all")
    List<User> getAllUser(){
        return userService.getAllUser();
    }

    @PostMapping("/updateuser")
    User updateUser(User user) {
        return userService.updateUser(user);
    }

    @PostMapping("/del/{id}")
    void deleteUser(Integer userid){
        userService.deleteUser(userid);
    }

    @PostMapping("/getbyid/{id}")
    User getUserByID(Integer userid){
        return userService.getUserByID(userid);
    }

}
