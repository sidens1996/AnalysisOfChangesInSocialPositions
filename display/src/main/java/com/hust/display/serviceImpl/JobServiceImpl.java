package com.hust.display.serviceImpl;

import com.hust.display.entity.Job;
import com.hust.display.repository.JobRepository;
import com.hust.display.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobServiceImpl implements JobService {

    @Autowired
    JobRepository jobRepository;

    @Override
    public List<Job> getAllJob() {
        return jobRepository.findAll();
    }
}
