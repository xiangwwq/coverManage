package com.lcy.mapper;

import com.lcy.dao.Config;

import java.util.List;

/**
* @author aaa
* @description 针对表【config】的数据库操作Mapper
* @createDate 2023-05-08 17:03:49
* @Entity com.lcy.dao.Config
*/
public interface ConfigMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Config record);

    int insertSelective(Config record);

    Config selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Config record);

    int updateByPrimaryKey(Config record);

   List<Config> selectAll();

   Config selectOneByName(String name);

}
