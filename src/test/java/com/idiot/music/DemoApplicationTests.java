package com.idiot.music;

import com.idiot.music.entity.SysRole;
import com.idiot.music.entity.SysUser;
import com.idiot.music.repository.SysRoleRepository;
import com.idiot.music.repository.SysUserRepository;
import com.idiot.music.service.SysUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {
    @Autowired
    private SysUserRepository userRepository;
    @Autowired
    private SysRoleRepository roleRepository;
    @Autowired
    private SysUserService userService;

    @Test
    public void contextLoads() {
       /* BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        SysRole role = new SysRole();
        role.setPower("ROLE_ADMIN");
        role.setName("系统管理员");
        roleRepository.save(role);
        SysUser admin = new SysUser();
        admin.setUsername("admin");
        admin.setPassword(encoder.encode("admin"));
        admin.setName("系统管理员");
        admin.getRoles().add(role);
        userRepository.save(admin);*/
    }

}
