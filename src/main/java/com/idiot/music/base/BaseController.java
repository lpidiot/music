package com.idiot.music.base;
/**
 * @auther idiot
 * @date 2019/12/13 - 17:28
 **/

import com.idiot.music.utils.GenericsUtils;
import com.sun.xml.internal.bind.v2.model.core.ID;
import org.springframework.beans.factory.annotation.Autowired;


/**
 *@ClassName:BaseController
 *@Description:TODO
 *@Version:1.0
 **/
public class BaseController<T>{
    @SuppressWarnings("unchecked")
    private Class<T> clazz=GenericsUtils.getSuperClassGenricType(getClass());
    @Autowired
    private BaseService<T, ID> baseService;

}
