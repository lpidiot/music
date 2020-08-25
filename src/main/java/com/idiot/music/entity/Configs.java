package com.idiot.music.entity;

import com.idiot.music.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Configs extends BaseEntity {
    private String mark;
    private String data;//内容

    @Column(unique = true)
    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        mark = mark;
    }

    @Column(columnDefinition = "text")
    public String getData() {
        return data;
    }
    public void setData(String data) {
        this.data = data;
    }

    public Configs(String mark, String data) {
        this.mark = mark;
        this.data = data;
    }

    public Configs() {
    }
}
