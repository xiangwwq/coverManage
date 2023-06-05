package com.lcy.dao;

import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

/**
 * 
 * @TableName config
 */
@Data
public class Config implements Serializable {
    /**
     * 
     */
    private Integer cid;

    /**
     * 名字
     */
    private String name;

    /**
     * 标准值
     */
    private BigDecimal standard;

    /**
     * 波动范围
     */
    private BigDecimal rangess;

    /**
     * 单位
     */
    private String unit;

    private static final long serialVersionUID = 1L;
}