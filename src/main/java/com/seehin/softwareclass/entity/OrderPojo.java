package com.seehin.softwareclass.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * @Description 订货信息
 * @Author Seehin
 * @Date 2020/5/5 0:18
 */
@Data
@NoArgsConstructor
public class OrderPojo {

    private Integer id;

    private String pNumber;

    private String pName;

    private String mainSupplier;

    private String minorSupplier;

    private int inNumber;

    private Timestamp createtime;
}
