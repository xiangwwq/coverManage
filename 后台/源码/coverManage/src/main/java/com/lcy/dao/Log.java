package com.lcy.dao;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName log
 */
@Data
public class Log implements Serializable {
    /**
     * 
     */
    private Integer lid;

    /**
     * 负责人
     */
    private String man;

    /**
     * 井的id
     */
    private Integer cid;

    public Log(String man, Integer cid, String msg) {
        this.man = man;
        this.cid = cid;
        this.msg = msg;
    }

    /**
     * 报警信息
     */
    private String msg;

    /**
     * 报警时间
     */
    private Date sendTime;

    /**
     * 是否处理
     */
    private Integer state;

    private static final long serialVersionUID = 1L;

    public Log() {

    }
}