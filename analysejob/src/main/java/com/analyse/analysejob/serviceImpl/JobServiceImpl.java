package com.analyse.analysejob.serviceImpl;

import com.analyse.analysejob.entity.Job;
import com.analyse.analysejob.repository.JobRepository;
import com.analyse.analysejob.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class JobServiceImpl implements JobService {

    @Autowired
    JobRepository jobRepository;

    @Override
    public Page<Job> getJobsByKeyword(Job job, Pageable pageable) {
        return jobRepository.getJobsByCondition(job.getJob_name(), job.getJob_city(), job.getTags(), pageable);
    }

    @Override
    public Job updateJob(Job job) {
        return jobRepository.save(job);
    }

    @Override
    public void deleteJobById(String jid) {
        jobRepository.deleteById(jid);
    }

    @Override
    public void deleteJobsByIds(String jids) {
        String[] jobids = jids.split(",");
        for (int i = 0; i < jobids.length; i++) {
            jobRepository.deleteById(jobids[i]);
        }
    }
}
