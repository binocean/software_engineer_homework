package com.seehin.softwareclass.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description 供应商
 * @Author Seehin
 * @Date 2020/5/5 0:16
 */
@Data
@NoArgsConstructor
public class SupplierPojo {

    private Integer id;

    private String name;

    private String phone;

    private String address;

}
