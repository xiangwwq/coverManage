package com.lcy.controller;

import com.lcy.commer.R;
import com.lcy.dao.Config;
import com.lcy.service.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author aaa
 * @description: TODO
 * @date: 2023/5/8 15:14
 */
@RestController
@RequestMapping("config")
public class ConfigController {

    @Autowired
    private ConfigService configService;


    @RequestMapping("list")
    public R list(){
        List<Config> configs = configService.selectAll();
        return R.ok().data("list",configs);
    }

    @PostMapping("update")
    public R update(@RequestBody Config config){
        System.out.println(config);
        boolean b = configService.updateByPrimaryKeySelective(config);
        return b?R.ok().message("更新成功"):R.error().message("数据无变化");
    }
}
