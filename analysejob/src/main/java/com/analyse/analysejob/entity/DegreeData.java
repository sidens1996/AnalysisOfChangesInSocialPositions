package com.analyse.analysejob.entity;

public class DegreeData {
    private String name;
    private Integer value;

    public String getName() {
        return name;
    }

    public DegreeData(String name, Integer value) {
        super();
        this.name = name;
        this.value = value;
    }

    public DegreeData() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }
}
