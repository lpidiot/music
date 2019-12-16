package com.idiot.music.entity;
/**
 * @auther idiot
 * @date 2019/9/15 - 14:22
 **/

import javax.persistence.*;

/**
 * @ClassName:SysRole
 * @Description:TODO
 * @Version:1.0
 **/
@Entity
public class SysRole{
    private Integer Id;
    private String name;//权限名
    private String power;//权限

    @Id
    @GeneratedValue
    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    @Column(length = 20)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(length = 20)
    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }
}
