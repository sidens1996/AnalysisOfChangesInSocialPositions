package com.analyse.analysejob.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Job {
    @Id
    private String url_object_id;
    private String job_name = "";
    private String salary;
    private String job_city = "";
    private String work_years = "";
    private String degree_need = "";
    private String tags = "";
    private String job_description;
    private String company_field;
    private String company_development = "";
    private String company_scale = "";
    private Date publish_time;
    private String url;

    public String getUrl_object_id() {
        return url_object_id;
    }

    public void setUrl_object_id(String url_object_id) {
        this.url_object_id = url_object_id;
    }

    public String getJob_name() {
        return job_name;
    }

    public void setJob_name(String job_name) {
        this.job_name = job_name;
    }

    public String getSalary() {
        return salary;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getJob_city() {
        return job_city;
    }

    public void setJob_city(String job_city) {
        this.job_city = job_city;
    }

    public String getWork_years() {
        return work_years;
    }

    public void setWork_years(String work_years) {
        this.work_years = work_years;
    }

    public String getDegree_need() {
        return degree_need;
    }

    public void setDegree_need(String degree_need) {
        this.degree_need = degree_need;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getJob_description() {
        return job_description;
    }

    public void setJob_description(String job_description) {
        this.job_description = job_description;
    }

    public String getCompany_field() {
        return company_field;
    }

    public void setCompany_field(String company_field) {
        this.company_field = company_field;
    }

    public String getCompany_development() {
        return company_development;
    }

    public void setCompany_development(String company_development) {
        this.company_development = company_development;
    }

    public String getCompany_scale() {
        return company_scale;
    }

    public void setCompany_scale(String company_scale) {
        this.company_scale = company_scale;
    }

    public Date getPublish_time() {
        return publish_time;
    }

    public void setPublish_time(Date publish_time) {
        this.publish_time = publish_time;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
