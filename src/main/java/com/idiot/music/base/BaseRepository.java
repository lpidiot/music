package com.idiot.music.base;
/**
 * @auther idiot
 * @date 2019/9/15 - 14:17
 **/

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @InterfaceName:CommonRepository
 * @Description:TODO
 * @Version:1.0
 **/
@NoRepositoryBean
public interface BaseRepository<T, ID> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {
}
