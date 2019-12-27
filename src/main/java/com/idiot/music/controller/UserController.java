package com.idiot.music.controller;
/**
 * @auther idiot
 * @date 2019/12/13 - 15:14
 **/

import com.alibaba.fastjson.JSONObject;
import com.idiot.music.annotation.PassToken;
import com.idiot.music.annotation.UserLoginToken;
import com.idiot.music.entity.SysUser;
import com.idiot.music.service.SysUserService;
import com.idiot.music.service.TokenService;
import com.idiot.music.utils.AjaxResponse;
import com.idiot.music.utils.HttpRequest;
import com.idiot.music.utils.IdiotUtils;
import org.jsoup.Jsoup;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

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
    public Object autRuntimeException(RuntimeException e) {

        e.printStackTrace();
        return AjaxResponse.error(e.getMessage());
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

    /**
     * 获取数据
     *
     * @return
     */
    @PassToken
    @PostMapping("/musicUtil")
    public Object musicUtil(@RequestBody JSONObject jsonParam) {
        String url = jsonParam.getString("url");
        if (jsonParam.getString("url") == null) {
            return AjaxResponse.error("目标url地址为空！");
        }
        JSONObject headerJson = jsonParam.getJSONObject("header");
        JSONObject paramsJson = jsonParam.getJSONObject("params");
        HashMap<String, String> header = IdiotUtils.jsonToHaMap(headerJson);
        HashMap<String, String> params = IdiotUtils.jsonToHaMap(paramsJson);
        return HttpRequest.sendGet(url, header, params);
    }
}
