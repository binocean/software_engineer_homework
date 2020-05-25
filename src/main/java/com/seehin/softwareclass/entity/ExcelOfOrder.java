package com.seehin.softwareclass.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @Description [todo]
 * @Author Seehin
 * @Date 2020/5/10 4:36
 */
public class ExcelOfOrder {

    @Excel(name = "ID",orderNum = "1")
    private Integer id;

    @Excel(name = "零件编号",orderNum = "2")
    private String pNumber;

    @Excel(name = "零件名称",orderNum = "3")
    private String pName;

    @Excel(name = "主供应商编号",orderNum = "4")
    private String mainSupplier;

    @Excel(name = "主供应商电话",orderNum = "5")
    private String mainPhone;

    @Excel(name = "次供应商编号",orderNum = "6")
    private String minorSupplier;

    @Excel(name = "次供应商编号",orderNum = "7")
    private String minorPhone;

    @Excel(name = "进货数量",orderNum = "8")
    private int inNumber;

    @Excel(name = "更新时间",orderNum = "9")
    private Timestamp createtime;

    public ExcelOfOrder(){}

    public ExcelOfOrder(
            Integer id,
            String pNumber,
            String pName,
            String mainSupplier,
            String minorSupplier,
            int inNumber,
            Timestamp createtime
    ){
        this.id = id;
        this.pNumber = pNumber;
        this.pName = pName;
        this.mainSupplier = mainSupplier;
        this.minorSupplier = minorSupplier;
        this.inNumber = inNumber;
        this.createtime = createtime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getpNumber() {
        return pNumber;
    }

    public void setpNumber(String pNumber) {
        this.pNumber = pNumber;
    }

    public String getpName() {
        return pName;
    }

    public void setpName(String pName) {
        this.pName = pName;
    }

    public String getMainSupplier() {
        return mainSupplier;
    }

    public void setMainSupplier(String mainSupplier) {
        this.mainSupplier = mainSupplier;
    }

    public String getMainPhone() {
        return mainPhone;
    }

    public void setMainPhone(String mainPhone) {
        this.mainPhone = mainPhone;
    }

    public String getMinorSupplier() {
        return minorSupplier;
    }

    public void setMinorSupplier(String minorSupplier) {
        this.minorSupplier = minorSupplier;
    }

    public String getMinorPhone() {
        return minorPhone;
    }

    public void setMinorPhone(String minorPhone) {
        this.minorPhone = minorPhone;
    }

    public int getInNumber() {
        return inNumber;
    }

    public void setInNumber(int inNumber) {
        this.inNumber = inNumber;
    }

    public Timestamp getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Timestamp createtime) {
        this.createtime = createtime;
    }
}
