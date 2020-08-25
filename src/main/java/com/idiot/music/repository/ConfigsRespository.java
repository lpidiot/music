package com.idiot.music.repository;

import com.idiot.music.base.BaseRepository;
import com.idiot.music.entity.Configs;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface ConfigsRespository extends BaseRepository<Configs,Integer> {
    Configs findByMark(String mark);
    @Transactional
    void deleteByMark(String mark);

}
