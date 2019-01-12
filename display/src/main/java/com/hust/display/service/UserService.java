package com.hust.display.service;

import com.hust.display.entity.User;

import java.util.List;

public interface UserService {

    User addUser(User user);

    List<User> getAllUser();

    User updateUser(User user);

    void deleteUser(User user);

    User getUserByName(String userName);
}

