package com.analyse.analysejob;

import com.analyse.analysejob.entity.Job;
import com.analyse.analysejob.entity.User;
import com.analyse.analysejob.repository.JobRepository;
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
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
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
    private JobRepository jobRepository;
    @Autowired
    private JobService jobService;

    @Test
    public void query() {
        PageRequest request = PageRequest.of(0, 10);
        Job job = new Job();
        job.setJob_name("java");
        Page<Job> jobs = jobService.findByCondition(job, request);
        System.out.println(jobs.getTotalPages());
    }


    public void test() {
        List citys = new ArrayList();
        List names = new ArrayList();
        citys.add("北京");
        names.add("经验1-3年");
//        System.out.println(citys);
    }
    public void contextLoads() {
        Specification s1 = new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Predicate p1 = criteriaBuilder.equal(root.get("rid"),"2");
                Predicate p2 = criteriaBuilder.equal(root.get("rid"),"3");
                return criteriaBuilder.or(p1,p2);
            }
        };
//第二个Specification定义了两个or的组合
        Specification s2 = new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
                Predicate p1 = criteriaBuilder.like(root.get("name"),"r%");
                Predicate p2 = criteriaBuilder.like(root.get("name"),"a%");
                return criteriaBuilder.or(p1,p2);
            }
        };

//        Job job = new Job();
//        PageRequest request = PageRequest.of(0, 10);
//        Page<Job> jobs = jobService.getJobsByKeyword(job, request);
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

