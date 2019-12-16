package com.idiot.music.base;
/**
 * @auther idiot
 * @date 2019/9/15 - 14:22
 **/

import javax.persistence.*;

/**
 * @ClassName:CommonEntity
 * @Description:TODO
 * @Version:1.0
 **/
@MappedSuperclass
public class BaseEntity {
    private Integer Id;

    @Id
    @GeneratedValue
    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }
}
