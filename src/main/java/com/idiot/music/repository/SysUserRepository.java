package com.idiot.music.repository;
/**
 * @auther idiot
 * @date 2019/9/15 - 14:41
 **/

import com.idiot.music.base.BaseRepository;
import com.idiot.music.entity.SysUser;
import org.springframework.stereotype.Repository;

/**
 *@ClassName:SysUserRepository
 *@Description:TODO
 *@Version:1.0
 **/
@Repository
public interface SysUserRepository extends BaseRepository<SysUser,Integer> {
    SysUser findByUsername(String username);
}
