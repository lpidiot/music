package com.idiot.music.entity;
/**
 * @auther idiot
 * @date 2019/9/15 - 14:22
 **/

import com.idiot.music.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * @ClassName:SysRole
 * @Description:TODO
 * @Version:1.0
 **/
@Entity
public class SysRole extends BaseEntity {
    private String name;//权限名
    private String power;//权限

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
