package com.hust.display.controller;

import com.hust.display.entity.User;
import com.hust.display.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
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

    @PostMapping("/del/{userName}")
    void deleteUser(@PathVariable("userName") String userName){
        userService.deleteUser(userName);
    }

//    @PostMapping("/delid/{userid}")
//    void deleteByID(@PathVariable("userid")Integer userid){
//        userService.deleteByID(userid);
//    }

    @PostMapping("/getbyname/{userName}")
    User getUserByID(@PathVariable("userName")String userName){
        return userService.getUserByName(userName);
    }

}
