package com.analyse.analysejob.serviceImpl;

import com.analyse.analysejob.entity.User;
import com.analyse.analysejob.repository.UserRepository;
import com.analyse.analysejob.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import org.springframework.data.domain.Pageable;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    //增加用户
    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) { return userRepository.save(user); }

    //验证用户
    @Override
    public User validateUser(User user) {
        return userRepository.queryUserByUsernameAndPassword(user.getUsername(), user.getPassword());
    }

    @Override
    public Page<User> getUsersByKeyword(User user, Pageable pageable) {
        return userRepository.getUsersByCondition(user.getUsername(), user.getRealname(), user.getProfession(), pageable);
    }

    @Override
    public void deleteUserById(Integer uid) {
        userRepository.deleteById(uid);
    }

    @Override
    public void deleteUsersByIds(String uids) {
        String[] userids = uids.split(",");
        for (int i = 0; i < userids.length; i++) {
            userRepository.deleteById(Integer.parseInt(userids[i]));
        }
    }
}
