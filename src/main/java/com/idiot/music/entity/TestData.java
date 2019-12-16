package com.idiot.music.entity;
/**
 * @auther idiot
 * @date 2019/12/13 - 15:24
 **/

import com.idiot.music.base.BaseEntity;

import javax.persistence.Entity;

/**
 *@ClassName:TestData
 *@Description:TODO
 *@Version:1.0
 **/
@Entity
public class TestData extends BaseEntity {
    private String name;
    private String age;
    private String sex;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
