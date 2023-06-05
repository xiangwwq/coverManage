package com.lcy.mapper;

import com.lcy.dao.Log;

import java.util.List;

/**
* @author aaa
* @description 针对表【log】的数据库操作Mapper
* @createDate 2023-05-08 17:53:58
* @Entity com.lcy.dao.Log
*/
public interface LogMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Log record);

    int insertSelective(Log record);

    Log selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Log record);

    int updateByPrimaryKey(Log record);

     List<Log> selectAll();

     int updateStateByLid(int lid,int state);

     List<Log> selectAllByState();

      int deleteAll();

}
