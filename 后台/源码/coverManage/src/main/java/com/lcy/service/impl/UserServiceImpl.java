package com.lcy.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lcy.dao.User;
import com.lcy.mapper.UserMapper;
import com.lcy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author aaa
 * @description: TODO
 * @date: 2023/5/4 9:35
 */
@Service
public class UserServiceImpl  implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User QueryUserByUid(int uid) {
        User user = userMapper.selectByPrimaryKey((long) uid);
        return user;
    }

    @Override
    public User QueryUser(User user) {
      return   userMapper.QueryUser(user);
    }

    @Override
    public PageInfo<User> QueryUserByName(int currentPage,int pageSize,String name) {
        PageHelper.startPage(currentPage, pageSize);
          List<User> users =  userMapper.QueryUserByName(name);
        PageInfo<User> pageInfo = new PageInfo<>(users);
        return pageInfo;
    }

    @Override
    public List<User> selectAll() {
        return  userMapper.selectAll();
    }

    @Override
    public boolean insertSelective(User record) {
        int i = userMapper.insertSelective(record);
        return i>0?true:false;
    }

    @Override
    public boolean deleteByPrimaryKey(Long id) {
        int  i=userMapper.deleteByPrimaryKey(id);
        return i>0?true:false;
    }
}
