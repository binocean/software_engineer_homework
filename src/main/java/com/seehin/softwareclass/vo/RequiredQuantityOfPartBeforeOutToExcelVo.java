package com.seehin.softwareclass.vo;

import lombok.Data;

/**
 * @Description [todo]
 * @Author Seehin
 * @Date 2020/5/12 9:37
 */
@Data
public class RequiredQuantityOfPartBeforeOutToExcelVo {

    private String pNumber;

    private int pMax;

    private int pQuantity;

    public RequiredQuantityOfPartBeforeOutToExcelVo(String pNumber, int pMax, int pQuantity) {
        this.pNumber = pNumber;
        this.pMax = pMax;
        this.pQuantity = pQuantity;
    }
}
