package com.idiot.music.base;
/**
 * @auther idiot
 * @date 2019/12/13 - 17:28
 **/

import com.idiot.music.utils.GenericsUtils;


/**
 *@ClassName:BaseController
 *@Description:TODO
 *@Version:1.0
 **/
public class BaseController<T>{
    private Class<T> clazz=GenericsUtils.getSuperClassGenricType(getClass());

}
