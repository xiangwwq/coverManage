package com.lcy.controller;

import com.lcy.commer.R;
import com.lcy.dao.Cover;
import com.lcy.service.CoverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author aaa
 * @description: TODO
 * @date: 2023/5/8 10:03
 */
@RestController
@RequestMapping("/cover")
public class CoverController {

   @Autowired
   private CoverService coverService;

    @RequestMapping("list")
       public R list(){
        List<Cover> coverList = coverService.selectAll();
        return R.ok().data("list",coverList);
    }
    //查询单个井盖
    @RequestMapping("{cid}")
    public R getOne(@PathVariable long cid){
        Cover cover = coverService.selectByPrimaryKey(cid);
        return R.ok().data("one",cover);
    }
    //删除井盖
    @DeleteMapping("{cid}")
    public R delete(@PathVariable long cid){
        boolean b = coverService.deleteByPrimaryKey(cid);
        return b?R.ok():R.error();
    }


    @RequestMapping("add")
    public R add(@RequestBody Cover cover){
        System.out.println(cover);
        boolean b = coverService.insertSelective(cover);
        return b?R.ok():R.error();
    }

}
