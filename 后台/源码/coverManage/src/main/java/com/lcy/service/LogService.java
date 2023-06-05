package com.lcy.service;

import com.github.pagehelper.PageInfo;
import com.lcy.dao.Log;

/**
 * @author aaa
 * @description: TODO
 * @date: 2023/5/8 17:54
 */
public interface LogService {

    PageInfo<Log> selectAll(int currentPage, int pageSize);

    boolean updateStateByLid(int lid,int state);

    PageInfo<Log> selectWarn(int currentPage, int pageSize);

    void  refresh(String name);
}
