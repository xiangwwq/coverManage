package com.lcy.service;

import com.lcy.dao.Cover;

import java.util.List;

/**
 * @author aaa
 * @description: TODO
 * @date: 2023/5/8 11:29
 */
public interface CoverService {
    List<Cover> selectAll();
    Cover selectByPrimaryKey(Long id);

    boolean deleteByPrimaryKey(Long id);

    boolean insertSelective(Cover record);
}
