package com.analyse.analysejob.service;

import com.analyse.analysejob.entity.Job;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface JobService {
    //根据关键字返回数据
    Page<Job> getJobsByKeyword(Job job, Pageable pageable);

    //修改数据
    Job updateJob(Job job);

    //删除数据
    void deleteJobById(String jid);

    //批量删除数据
    void deleteJobsByIds(String jids);

    //条件筛选数据
    Page<Job> findByCondition(Job job, Pageable pageable);
}
