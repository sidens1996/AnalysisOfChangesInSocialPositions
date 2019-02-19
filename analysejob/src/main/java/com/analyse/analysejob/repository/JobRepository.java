package com.analyse.analysejob.repository;

import com.analyse.analysejob.entity.Job;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JobRepository extends JpaRepository<Job, String>, JpaSpecificationExecutor<Job> {
    @Query("select job from Job job where job.job_name like %:job_name% or job.job_city like %:job_city% or job.tags like %:tags%")
    Page<Job> getJobsByCondition(@Param("job_name") String job_name, @Param("job_city") String job_city, @Param("tags") String tags, Pageable pageable);

//    @Query(value = "select * from job where job_city in (:clist) and work_years in (:nlist)", nativeQuery = true)
//    List<Job> getJobsByids(@Param("clist") List<String> clist, @Param("nlist") List<String> nlist);
}
