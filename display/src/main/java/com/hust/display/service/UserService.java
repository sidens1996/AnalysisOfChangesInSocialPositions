package com.hust.display.service;

import com.hust.display.entity.User;

import javax.transaction.Transactional;
import java.util.List;

public interface UserService {

    User addUser(User user);

    List<User> getAllUser();

    User updateUser(User user);

    void deleteUser(String userName);

    //void deleteByID(Integer userid);

    User getUserByName(String userName);
}

