package com.lcy.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lcy.dao.Config;
import com.lcy.dao.Cover;
import com.lcy.dao.Log;
import com.lcy.dao.User;
import com.lcy.mapper.ConfigMapper;
import com.lcy.mapper.CoverMapper;
import com.lcy.mapper.LogMapper;
import com.lcy.service.CoverService;
import com.lcy.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

/**
 * @author aaa
 * @description: TODO
 * @date: 2023/5/8 17:54
 */
@Service
public class LogServiceImpl implements LogService {

    @Autowired
    private LogMapper logMapper;


    @Autowired
    private CoverMapper coverMapper;

    @Autowired
    private ConfigMapper configMapper;

     public PageInfo<Log> selectAll(int currentPage, int pageSize){
         PageHelper.startPage(currentPage,pageSize);

         List<Log> logs = logMapper.selectAll();
         PageInfo<Log> pageInfo = new PageInfo<>(logs);
         return pageInfo;
     }

     public void  refresh(String name){
         //先清空未处理的警报
         logMapper.deleteAll();
         //检测未处理问题
         List<Cover> covers = coverMapper.selectAll();
         Config   H2S=  configMapper.selectOneByName("硫化氢标准值");
         Config   CH4=  configMapper.selectOneByName("甲烷标准值");
         Config   H20=  configMapper.selectOneByName("水位标准值");
         Config   qxjd=  configMapper.selectOneByName("倾斜角度");
         Config   jlwd=  configMapper.selectOneByName("井内温度");

         for (Cover  cr:  covers){
             if(cr.getH2s().compareTo(H2S.getStandard().add(H2S.getRangess().divide(new BigDecimal(2),0,BigDecimal.ROUND_HALF_UP)))==-1){
                 Log log=  new Log(name,Integer.parseInt(cr.getCid()),"硫化氢含量过低");
                 logMapper.insert(log);
             }
             if(cr.getH2s().compareTo(H2S.getStandard().add(H2S.getRangess().divide(new BigDecimal(2),0,BigDecimal.ROUND_HALF_UP)))==1){
                 Log log=  new Log(name,Integer.parseInt(cr.getCid()),"硫化氢含量过高");
                 logMapper.insert(log);
             }

             if(cr.getH2s().compareTo(CH4.getStandard().add(CH4.getRangess().divide(new BigDecimal(2),0,BigDecimal.ROUND_HALF_UP)))==-1){
                 Log log=  new Log(name,Integer.parseInt(cr.getCid()),"甲烷含量过低");
                 logMapper.insert(log);
             }

             if(cr.getH2s().compareTo(CH4.getStandard().add(CH4.getRangess().divide(new BigDecimal(2),0,BigDecimal.ROUND_HALF_UP)))==1){
                 Log log=  new Log(name,Integer.parseInt(cr.getCid()),"甲烷含量过高");
                 logMapper.insert(log);
             }

             if(cr.getH2s().compareTo(H20.getStandard().add(H20.getRangess().divide(new BigDecimal(2),0,BigDecimal.ROUND_HALF_UP)))==-1){
                 Log log=  new Log(name,Integer.parseInt(cr.getCid()),"水位过低");
                 logMapper.insert(log);
             }

             if(cr.getH2s().compareTo(H20.getStandard().add(H20.getRangess().divide(new BigDecimal(2),0,BigDecimal.ROUND_HALF_UP)))==1){
                 Log log=  new Log(name,Integer.parseInt(cr.getCid()),"水位过高");
                 logMapper.insert(log);
             }


             if((cr.getH2s().compareTo(qxjd.getStandard().add(qxjd.getRangess().divide(new BigDecimal(2),0,BigDecimal.ROUND_HALF_UP)))==-1)
                     ||(cr.getH2s().compareTo(qxjd.getStandard().add(qxjd.getRangess().divide(new BigDecimal(2),0,BigDecimal.ROUND_HALF_UP)))==1)
             ){
                 Log log=  new Log(name,Integer.parseInt(cr.getCid()),"倾斜角度异常");
                 logMapper.insert(log);
             }

             if(cr.getH2s().compareTo(jlwd.getStandard().add(jlwd.getRangess().divide(new BigDecimal(2),0,BigDecimal.ROUND_HALF_UP)))==1){
                 Log log=  new Log(name,Integer.parseInt(cr.getCid()),"井内温度过高");
                 logMapper.insert(log);
             }

             if(cr.getH2s().compareTo(jlwd.getStandard().add(jlwd.getRangess().divide(new BigDecimal(2),0,BigDecimal.ROUND_HALF_UP)))==-1){
                 Log log=  new Log(name,Integer.parseInt(cr.getCid()),"井内温度过低");
                 logMapper.insert(log);
             }
         }
     }

     public  PageInfo<Log> selectWarn(int currentPage, int pageSize){

        PageHelper.startPage(currentPage,pageSize);

         List<Log> logs = logMapper.selectAllByState();

         PageInfo<Log> pageInfo = new PageInfo<>(logs);

         return pageInfo;
     }


    @Override
    public boolean updateStateByLid(int lid, int state) {
        int i = logMapper.updateStateByLid(lid, state);
        return i>0?true:false ;
    }
}
