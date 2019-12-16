package com.idiot.music.annotation;
/**
 * @auther idiot
 * @date 2019/12/14 - 12:21
 **/

import java.lang.annotation.*;

/**
 * @ClassName:PassToken
 * @Description:TODO
 * @Version:1.0
 **/
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface PassToken {
    boolean required() default true;
}
