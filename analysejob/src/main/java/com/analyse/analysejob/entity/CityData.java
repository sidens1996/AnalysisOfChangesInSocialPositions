package com.analyse.analysejob.entity;

public class CityData {
    private String name;
    private Integer value;

    public CityData(String name, Integer value) {
        super();
        this.name = name;
        this.value = value;
    }

    public CityData() {
    }

    public String getName() {
        return name;
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
