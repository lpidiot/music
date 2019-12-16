package com.idiot.music.controller;
/**
 * @auther idiot
 * @date 2019/9/15 - 14:20
 **/

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName:DemoController
 * @Description:基础控制器-登录等操作
 * @Version:1.0
 **/
@Controller
public class BasicController {
    @RequestMapping(value = {"/login","/"}, method = RequestMethod.GET)
    public String login(@RequestParam(value = "error", required = false) String error, @RequestParam(value = "logout", required = false) String logout, ModelMap map) {
        if (error != null) {
            map.put("msg", "用户名或密码错误！");
        }
        if (logout != null) {
            map.put("msg", "注销成功");
        }
        return "login";
    }

    @RequestMapping(value = "/checkLogin",method = RequestMethod.POST)
    public String checkLogin(){
        return  "redirect:/index";
    }


    @RequestMapping(value = "/index")
    public String index(){
        return "index";
    }
}
