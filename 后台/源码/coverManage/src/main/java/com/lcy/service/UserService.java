package com.lcy.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import com.lcy.dao.User;

import java.util.List;

/**
 * @author aaa
 * @description: TODO
 * @date: 2023/5/4 9:34
 */
public interface UserService {

    User QueryUserByUid(int a);

    User QueryUser(User user);

    PageInfo<User> QueryUserByName(int currentPage, int pageSize, String name);
    List<User>  selectAll();

    boolean insertSelective(User record);

    boolean deleteByPrimaryKey(Long id);


}
