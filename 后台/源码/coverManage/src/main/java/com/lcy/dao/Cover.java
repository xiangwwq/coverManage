package com.lcy.dao;

import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

/**
 * 
 * @TableName cover
 */
@Data
public class Cover implements Serializable {
    /**
     * 
     */
    private String cid;

    /**
     * 水位 单位cm
     */
    private BigDecimal waterLevel;

    /**
     * 硫化氢 单位%
     */
    private BigDecimal h2s;

    /**
     * 甲烷 单位%
     */
    private BigDecimal ch4;

    /**
     * 倾角
     */
    private BigDecimal inclination;

    /**
     * 温度 单位摄氏度
     */
    private BigDecimal temperature;

    /**
     * 经度
     */
    private Integer gpsEw;

    /**
     * 纬度
     */
    private Integer gpsNs;

    /**
     * 负责人
     */
    private String man;

    /**
     * 所属地区
     */
    private String address;

    private static final long serialVersionUID = 1L;
}