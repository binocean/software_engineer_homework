package com.seehin.softwareclass.entity;

import lombok.Data;

/**
 * @Description 零件信息
 * @Author Seehin
 * @Date 2020/5/4 22:34
 */
@Data
public class PartPojo {

    private Integer id;

    private String pNumber;

    private String pName;

    private String pPrice;

    private String mainSupplier;

    private String minorSupplier;

}
