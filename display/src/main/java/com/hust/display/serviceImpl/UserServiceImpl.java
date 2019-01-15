package com.hust.display.serviceImpl;

import com.hust.display.entity.User;
import com.hust.display.repository.UserRepository;
import com.hust.display.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User addUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(String userName) {
        userRepository.deleteUser(userName);
    }

//    @Override
//    public void deleteByID(Integer userid) {
//        userRepository.deleteById(userid);
//    }

    @Override
    public User getUserByName(String userName) {
        return userRepository.getUserByName(userName);
    }
}
