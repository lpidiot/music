package com.idiot.music.service;

import com.idiot.music.base.BaseService;
import com.idiot.music.entity.Configs;
import com.idiot.music.repository.ConfigsRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfigsService extends BaseService<Configs,Integer> {
    @Autowired
    private ConfigsRespository configsRespository;

    public Configs findByMark(String mark){
        return configsRespository.findByMark(mark);
    }
    public void deleteByMark(String mark){
        configsRespository.deleteByMark(mark);
    }
}
