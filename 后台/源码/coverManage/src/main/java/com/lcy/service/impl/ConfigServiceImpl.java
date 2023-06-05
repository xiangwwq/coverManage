package com.lcy.service.impl;

import com.lcy.dao.Config;
import com.lcy.mapper.ConfigMapper;
import com.lcy.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author aaa
 * @description: TODO
 * @date: 2023/5/8 15:15
 */
@Service
public class ConfigServiceImpl implements ConfigService {

    @Autowired
    private ConfigMapper configMapper;

    @Override
    public List<Config> selectAll() {
        return  configMapper.selectAll();
    }

    @Override
    public boolean updateByPrimaryKeySelective(Config record) {
        int i = configMapper.updateByPrimaryKeySelective(record);
        return i>0?true:false;
    }


}
