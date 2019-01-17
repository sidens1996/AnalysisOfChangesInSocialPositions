package com.analyse.analysejob;

import com.analyse.analysejob.entity.Job;
import com.analyse.analysejob.entity.User;
import com.analyse.analysejob.repository.UserRepository;
import com.analyse.analysejob.service.JobService;
import com.analyse.analysejob.service.UserService;
import com.analyse.analysejob.serviceImpl.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AnalysejobApplicationTests {

    @Autowired
    private UserService userService;
    @Autowired
    private UserServiceImpl userServiceimpl;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JobService jobService;

    @Test
    public void contextLoads() {
//        jobService.deleteJobById("xixi");
//        Job job = new Job();
//        PageRequest request = PageRequest.of(1, 10);
//        Page<Job> jobs = jobService.getJobsByKeyword(job, request);
//        System.out.println(jobs.getTotalPages());
//        userService.deleteUsersByIds("136,137,138");
//        User user = new User();
//        user.setUsername("test");
//        user.setPassword("test");
//        user.setProfession("test");
//        user.setRealname("test");
//        user.setAge(12);
//        user.setDegree("test");
//        user.setDescription("test");
//        user.setEmail("test");
//        user.setRole("test");
//        user.setStatus(true);
//        user.setTelephone("test");
//        user.setWorkyears("test");
//        user.setSex("test");
//        userServiceimpl.addUser(user);
    }
}

