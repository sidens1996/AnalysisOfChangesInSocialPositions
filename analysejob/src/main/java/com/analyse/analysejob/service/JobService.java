package com.analyse.analysejob.service;

import com.analyse.analysejob.entity.Job;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface JobService {
    //根据关键字返回用户
    Page<Job> getJobsByKeyword(Job job, Pageable pageable);

    //修改用户
    Job updateJob(Job job);

    //删除用户
    void deleteJobById(String jid);

    //批量删除用户
    void deleteJobsByIds(String jids);
}
