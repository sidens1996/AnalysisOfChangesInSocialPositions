package com.hust.display.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {

    @Id
    @GeneratedValue
    private Integer userid;         //用户id
    @Column(unique = true)
    private String userName;        //用户名
    private String passWord;        //密码
    private String realName;        //真实姓名
    private String sex;             //性别
    private int age;                //年龄
    private String email;           //email
    private String telephone;       //电话
    private String degree;          //最高学历
    private String workyears;       //工作经验
    private String profession;      //专业
    private String role;            //角色
    private String status;          //状态
    private String description;     //描述

    public void setUserid(Integer userid) {
        this.userid = userid;
    }
    public Integer getUserid() {
        return userid;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getUserName() {
        return userName;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }
    public String getPassWord() {
        return passWord;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }
    public String getRealName() {
        return realName;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
    public String getSex() {
        return sex;
    }

    public void setAge(int age) {
        this.age = age;
    }
    public int getAge() {
        return age;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail() {
        return email;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    public String getTelephone() {
        return telephone;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }
    public String getDegree() {
        return degree;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }
    public String getProfession() {
        return profession;
    }

    public void setRole(String role) {
        this.role = role;
    }
    public String getRole() {
        return role;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    public String getStatus() {
        return status;
    }

    public void setWorkyears(String workyears) {
        this.workyears = workyears;
    }
    public String getWorkyears() {
        return workyears;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
