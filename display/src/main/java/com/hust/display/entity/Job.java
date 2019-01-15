package com.hust.display.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "job")
public class Job {

    @Id @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    @Column(name = "url_object_id")
    private String urlObjectID;

    @Column(name = "job_name")
    private String jobName;

    @Column(name = "salary")
    private String salary;

    @Column(name = "job_city")
    private String jobCity;

    @Column(name = "work_years")
    private String workYears;

    @Column(name = "degree_need")
    private String degreeNeed;

    @Column(name = "tags")
    private String tags;

    @Column(name = "job_description")
    private String jobDescription;

    @Column(name = "company_field")
    private String companyField;

    @Column(name = "company_development")
    private String companyDevelopment;

    @Column(name = "company_scale")
    private String companyScale;

    @Column(name = "publish_time")
    private String publishTime;

    @Column(name = "url")
    private String url;

    public void setUrlObjectID(String urlObjectID) {
        this.urlObjectID = urlObjectID;
    }

    public String getUrlObjectID() {
        return urlObjectID;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getJobName() {
        return jobName;
    }

    public void setSalary(String salary) {
        this.salary = salary;
    }

    public String getSalary() {
        return salary;
    }

    public void setJobCity(String jobCity) {
        this.jobCity = jobCity;
    }

    public String getJobCity() {
        return jobCity;
    }

    public void setWorkYears(String workYears) {
        this.workYears = workYears;
    }

    public String getWorkYears() {
        return workYears;
    }

    public void setDegreeNeed(String degreeNeed) {
        this.degreeNeed = degreeNeed;
    }

    public String getDegreeNeed() {
        return degreeNeed;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getTags() {
        return tags;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setCompanyField(String companyField) {
        this.companyField = companyField;
    }

    public String getCompanyField() {
        return companyField;
    }

    public void setCompanyDevelopment(String companyDevelopment) {
        this.companyDevelopment = companyDevelopment;
    }

    public String getCompanyDevelopment() {
        return companyDevelopment;
    }

    public void setCompanyScale(String companyScale) {
        this.companyScale = companyScale;
    }

    public String getCompanyScale() {
        return companyScale;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
