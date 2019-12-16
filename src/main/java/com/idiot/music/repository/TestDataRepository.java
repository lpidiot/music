package com.idiot.music.repository;
/**
 * @auther idiot
 * @date 2019/12/13 - 15:25
 **/

import com.idiot.music.base.BaseRepository;
import com.idiot.music.entity.TestData;
import org.springframework.stereotype.Repository;

/**
 *@ClassName:TestDataRepository
 *@Description:TODO
 *@Version:1.0
 **/
@Repository
public interface TestDataRepository extends BaseRepository<TestData,Integer> {
}
