package com.analyse.analysejob.entity;

import java.util.List;
import java.util.Map;

public class TotalData {
    private String jobname;
    private List<CityData> cityData;
    private List<DegreeData> degreeData;
    private String [] dataAxis;
    private Integer [] salarydata;
    private Map<String, Integer> keywords;


    public String getJobname() {
        return jobname;
    }

    public void setJobname(String jobname) {
        this.jobname = jobname;
    }

    public List<CityData> getCityData() {
        return cityData;
    }

    public void setCityData(List<CityData> cityData) {
        this.cityData = cityData;
    }

    public List<DegreeData> getDegreeData() {
        return degreeData;
    }

    public void setDegreeData(List<DegreeData> degreeData) {
        this.degreeData = degreeData;
    }

    public String[] getDataAxis() {
        return dataAxis;
    }

    public void setDataAxis(String[] dataAxis) {
        this.dataAxis = dataAxis;
    }

    public Integer[] getSalarydata() {
        return salarydata;
    }

    public void setSalarydata(Integer[] salarydata) {
        this.salarydata = salarydata;
    }

    public Map<String, Integer> getKeywords() {
        return keywords;
    }

    public void setKeywords(Map<String, Integer> keywords) {
        this.keywords = keywords;
    }
}
