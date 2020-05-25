package com.seehin.softwareclass.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * @Description 事务信息
 * @Author Seehin
 * @Date 2020/5/5 0:23
 */
@Data
@NoArgsConstructor
public class AffairPojo {

    private Integer id;

    private String listNumber;

    private String pNumber;

    private int outNumber;

    private Timestamp createtime;

    private int dispose;
}
