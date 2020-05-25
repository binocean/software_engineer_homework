package com.seehin.softwareclass.vo;

import lombok.Data;

/**
 * @Description 接收form表单传来的新增供应商信息
 * @Author Seehin
 * @Date 2020/5/6 22:01
 */
@Data
public class SupplierVo {

    private String name;

    private String phone;

    private String address;

}
