package com.analyse.analysejob.serviceImpl;

import com.analyse.analysejob.entity.Job;
import com.analyse.analysejob.repository.JobRepository;
import com.analyse.analysejob.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
public class JobServiceImpl implements JobService {

    @Autowired
    JobRepository jobRepository;

    @Override
    public Page<Job> getJobsByKeyword(Job job, Pageable pageable) {
        return jobRepository.getJobsByCondition(job.getJob_name(), pageable);
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

    @Override
    public Page<Job> findByCondition(Job job, Pageable pageable) {
        Page<Job> resultList = null;
        Specification querySpecification = new Specification<Job>() {
            @Override
            public Predicate toPredicate(Root<Job> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                List<Predicate> orpredicates = new ArrayList<>();
                String job_name = job.getJob_name();
                String job_city = job.getJob_city();
                String work_years = job.getWork_years();
                String degree_need = job.getDegree_need();
                String company_development = job.getCompany_development();
                String company_scale = job.getCompany_scale();
                String tags = job.getTags();
                //工作名称
                if (job_name != null && !job_name.equals("")) {
                    predicates.add(criteriaBuilder.like(root.get("job_name").as(String.class), "%" + job_name + "%"));
                }
                //工作地点
                if (job_city != null && !job_city.equals("")) {
                    CriteriaBuilder.In<Object> in = criteriaBuilder.in(root.get("job_city"));
                    String[] citys = job_city.split(",");
                    for (String city : citys) {
                        in.value(city);
                    }
                    predicates.add(in);
                }
                //工作经验
                if (work_years != null && !work_years.equals("")) {
                    CriteriaBuilder.In<Object> in = criteriaBuilder.in(root.get("work_years"));
                    String[] workyears = work_years.split(",");
                    for (String workyear : workyears) {
                        in.value(workyear);
                    }
                    predicates.add(in);
                }
                //学历
                if (degree_need != null && !degree_need.equals("")) {
                    CriteriaBuilder.In<Object> in = criteriaBuilder.in(root.get("degree_need"));
                    String[] degrees = {"学历不限", "大专及以上", "本科及以上", "硕士及以上"};
                    boolean flag = true;
                    for (String degree : degrees) {
                        if (flag) {
                            in.value(degree);
                        }
                        if (flag && degree_need.equals(degree)) {
                            flag = false;
                        }
                    }
                    predicates.add(in);
                }
                //公司发展
                if (company_development != null && !company_development.equals("")) {
                    CriteriaBuilder.In<Object> in = criteriaBuilder.in(root.get("company_development"));
                    String[] developments = company_development.split(",");
                    for (String development : developments) {
                        in.value(development);
                    }
                    predicates.add(in);
                }
                //公司规模
                if (company_scale != null && !company_scale.equals("")) {
                    CriteriaBuilder.In<Object> in = criteriaBuilder.in(root.get("company_scale"));
                    String[] scales = company_scale.split(",");
                    for (String scale : scales) {
                        in.value(scale);
                    }
                    predicates.add(in);
                }
                //标签
                if (tags != null && !tags.equals("")) {
                    String[] tagss = tags.split(",");
                    for (String tag : tagss) {
                        orpredicates.add(criteriaBuilder.like(root.get("tags").as(String.class), "%" + tag + "%"));
                    }
                }
                if (orpredicates.size() > 0) {
                    return criteriaBuilder.and(criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()])), criteriaBuilder.or(orpredicates.toArray(new Predicate[orpredicates.size()])));
                } else {
                    return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
                }
            }
        };
        resultList = jobRepository.findAll(querySpecification, pageable);
        return resultList;
    }
}
