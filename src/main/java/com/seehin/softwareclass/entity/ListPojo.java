package com.seehin.softwareclass.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Description 库存清单
 * @Author Seehin
 * @Date 2020/5/5 0:20
 */
@Data
public class ListPojo {

    private Integer id;

    private String listNumber;

    private String pNumber;

    private int pMax;

    private int pQuantity;

    private int pMin;

    public ListPojo(int pQuantity,int pMin){
        this.pQuantity = pQuantity;
        this.pMin = pMin;
    }

    public ListPojo(){}

    public ListPojo(int id){this.id = id;}
}
