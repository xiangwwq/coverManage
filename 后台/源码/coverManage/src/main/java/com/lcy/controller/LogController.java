package com.lcy.controller;

import com.github.pagehelper.PageInfo;
import com.lcy.commer.R;
import com.lcy.dao.Log;
import com.lcy.service.LogService;
import com.lcy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author aaa
 * @description: TODO
 * @date: 2023/5/8 18:00
 */
@RestController
@RequestMapping("/log")
public class LogController {
    @Autowired
    private LogService logService;

     // 获得日志
    @RequestMapping("list")
    public R list(@RequestParam( value = "currentPage",required = false,defaultValue = "1") int currentPage
            ,@RequestParam( value = "pageSize",required = false,defaultValue = "10") int pageSize){
          PageInfo<Log> logPageInfo = logService.selectAll(currentPage, pageSize);
        return R.ok().message("分页查询").data("page",logPageInfo);
    }

    //获得预警信息

    @RequestMapping("listWarn")
    public R listWarn(@RequestParam( value = "currentPage",required = false,defaultValue = "1") int currentPage
            ,@RequestParam( value = "pageSize",required = false,defaultValue = "10") int pageSize){

        PageInfo<Log> logPageInfo = logService.selectWarn(currentPage, pageSize);
        return R.ok().message("分页查询").data("page",logPageInfo);
    }

    @RequestMapping("refresh")
    public R refresh(){
        logService.refresh("测试0002");
        return R.ok().message("刷新成功");
    }


    //切换预警状态
    @RequestMapping("change")
    public R change(@RequestParam( value = "state",required = false,defaultValue = "1") boolean state
            ,@RequestParam( value = "lid",required = false,defaultValue = "10") int lid){
            boolean b = logService.updateStateByLid(lid, state==true?1:0);
            return b?R.ok().message("处理成功"):R.error().message("更新失败");
    }

    //刷新数据

}
