package com.idiot.music.repository;
/**
 * @auther idiot
 * @date 2019/9/15 - 14:40
 **/

import com.idiot.music.base.BaseRepository;
import com.idiot.music.entity.SysRole;
import org.springframework.stereotype.Repository;

/**
 *@InterfaceName:SysRoleRepository
 *@Description:TODO
 *@Version:1.0
 **/
@Repository
public interface SysRoleRepository extends BaseRepository<SysRole,Integer> {
}
