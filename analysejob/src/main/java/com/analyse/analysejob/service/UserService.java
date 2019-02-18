package com.analyse.analysejob.service;

import com.analyse.analysejob.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    //添加用户
    User addUser(User user);

    User validateUser(User user);
    //根据关键字返回用户
    Page<User> getUsersByKeyword(User user,Pageable pageable);

    //修改用户
    User updateUser(User user);

    //删除用户
    void deleteUserById(Integer uid);

    //批量删除用户
    void deleteUsersByIds(String uids);

}
