package com.idiot.music.service;
/**
 * @auther idiot
 * @date 2019/12/14 - 12:51
 **/

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.idiot.music.entity.SysUser;
import org.springframework.stereotype.Service;

/**
 * @ClassName:TokenService
 * @Description:TODO
 * @Version:1.0
 **/
@Service("TokenService")
public class TokenService {
    /**
     * @return
     * @Author
     * @Description TODO
     * @Date 12:30 2019/12/14
     * @Param
     **/
    public String getToken(SysUser user) {
        //Algorithm.HMAC256():使用HS256生成token,密钥则是用户的密码，唯一密钥的话可以保存在服务端。
        // withAudience()存入需要保存在token的信息，这里我把用户ID存入token中
        String token = "";
        token = JWT.create().withAudience(String.valueOf(user.getId()))
                .sign(Algorithm.HMAC256(user.getPassword()));
        return token;
    }
}
