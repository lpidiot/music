package com.idiot.music.service;
/**
 * @auther idiot
 * @date 2019/9/15 - 14:43
 **/

import com.idiot.music.base.BaseService;
import com.idiot.music.config.security.MyUser;
import com.idiot.music.entity.SysRole;
import com.idiot.music.entity.SysUser;
import com.idiot.music.repository.SysUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName:SysUserService
 * @Description:TODO
 * @Version:1.0
 **/
@Service
public class SysUserService extends BaseService<SysUser, Integer> implements UserDetailsService {
    @Autowired
    private SysUserRepository userRepository;

    public SysUser findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        List<GrantedAuthority> authorities = new ArrayList<>();
        List<SysRole> roles = user.getRoles();
        for (SysRole role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getPower()));
        }
        return new MyUser(user.getUsername(), user.getPassword(), user.getName(), authorities);
    }
}
