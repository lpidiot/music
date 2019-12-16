package com.idiot.music.controller;
/**
 * @auther idiot
 * @date 2019/12/13 - 15:14
 **/

import com.alibaba.fastjson.JSONObject;
import com.idiot.music.annotation.UserLoginToken;
import com.idiot.music.entity.SysUser;
import com.idiot.music.service.SysUserService;
import com.idiot.music.service.TokenService;
import com.idiot.music.utils.AjaxResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName:userRestController
 * @Description:TODO
 * @Version:1.0
 **/
@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private TokenService tokenService;

    @ResponseBody
    @ExceptionHandler({RuntimeException.class})
    public Object autRuntimeException() {
        return AjaxResponse.error("token验证失败，请重新登陆");
    }

    @PostMapping(value = "/login")
    public Object checkLogin(@RequestBody SysUser userData) {
        SysUser user = sysUserService.findByUsername(userData.getUsername());
        System.out.println(userData.getPassword());
        if (user == null) {
            return AjaxResponse.error("无此用户");
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        boolean flg = encoder.matches(userData.getPassword(), user.getPassword());
        if (flg) {
            JSONObject jsonObject = new JSONObject();
            String token = tokenService.getToken(user);
            jsonObject.put("token", token);
            jsonObject.put("user", user);
            return AjaxResponse.success(jsonObject);
        } else {
            return AjaxResponse.error("密码错误");
        }
    }

    @UserLoginToken
    @GetMapping("/getMessage")
    public Object getMessage() {
        return "你已通过验证";
    }

    @PostMapping(value = "/user")
    public AjaxResponse saveArticle(@RequestBody SysUser data) {
        try {
            sysUserService.save(data);
            return AjaxResponse.success("保存成功");
        } catch (Exception e) {
            return AjaxResponse.error(e.getMessage());
        }
    }

    @DeleteMapping(value = "/user/{id}")
    public AjaxResponse deleteUser(@PathVariable Integer id) {
        try {
            sysUserService.deleteById(id);
            return AjaxResponse.success("删除成功");
        } catch (Exception e) {
            return AjaxResponse.error(e.getMessage());
        }
    }

    @PutMapping(value = "/user/{id}")
    public AjaxResponse updateUser(@PathVariable Integer id, @RequestBody SysUser data) {
        try {
            SysUser userData = sysUserService.findById(id);
            BeanUtils.copyProperties(data, userData, "id");
            sysUserService.save(userData);
            return AjaxResponse.success();
        } catch (Exception e) {
            return AjaxResponse.error(e.getMessage());
        }
    }

    @GetMapping(value = "/user/{id}")
    public AjaxResponse getUser(@PathVariable Integer id) {
        try {
            return AjaxResponse.success(sysUserService.findById(id));
        } catch (Exception e) {
            return AjaxResponse.error(e.getMessage());
        }
    }
}
