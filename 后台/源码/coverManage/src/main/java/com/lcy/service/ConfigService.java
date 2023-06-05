package com.lcy.service;

import com.lcy.dao.Config;

import java.util.List;

/**
 * @author aaa
 * @description: TODO
 * @date: 2023/5/8 15:15
 */

public interface ConfigService {
    List<Config> selectAll();

    boolean updateByPrimaryKeySelective(Config record);


}
