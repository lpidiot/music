package com.idiot.music.annotation;

import java.lang.annotation.*;

/**
 * @auther idiot
 * @date 2019/12/14 - 12:26
 **/
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface UserLoginToken {
    boolean required() default true;
}
