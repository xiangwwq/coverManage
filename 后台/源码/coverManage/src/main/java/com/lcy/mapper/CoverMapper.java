package com.lcy.mapper;

import com.lcy.dao.Cover;

import java.util.List;

/**
* @author aaa
* @description 针对表【cover】的数据库操作Mapper
* @createDate 2023-05-08 11:26:47
* @Entity com.lcy.dao.Cover
*/
public interface CoverMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Cover record);

    int insertSelective(Cover record);

    Cover selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Cover record);

    int updateByPrimaryKey(Cover record);


    List<Cover> selectAll();

}
