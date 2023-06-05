package com.lcy.service.impl;

import com.lcy.dao.Cover;
import com.lcy.mapper.CoverMapper;
import com.lcy.service.CoverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author aaa
 * @description: TODO
 * @date: 2023/5/8 11:29
 */
@Service
public class CoverServiceImpl  implements CoverService {
    @Autowired
    private CoverMapper coverMapper;


    @Override
    public List<Cover> selectAll() {
      return   coverMapper.selectAll();
    }

    @Override
    public Cover selectByPrimaryKey(Long id) {
        return coverMapper.selectByPrimaryKey(id);
    }

    @Override
    public boolean deleteByPrimaryKey(Long id) {
        int i = coverMapper.deleteByPrimaryKey(id);
        return i>0?true:false;
    }

    @Override
    public boolean insertSelective(Cover record) {
        int i = coverMapper.insertSelective(record);
        return i>0?true:false;
    }
}
