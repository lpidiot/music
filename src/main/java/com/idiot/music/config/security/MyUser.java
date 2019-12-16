package com.idiot.music.config.security;
/**
 * @auther idiot
 * @date 2019/9/15 - 14:49
 **/

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

/**
 * @ClassName:MyUser
 * @Description:拼上name的user对象==
 * @Version:1.0
 **/
public class MyUser extends User {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MyUser(String username, String password, String name, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.name=name;
    }
}
