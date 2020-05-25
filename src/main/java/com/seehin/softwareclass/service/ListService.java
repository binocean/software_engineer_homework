package com.seehin.softwareclass.service;

import com.seehin.softwareclass.entity.ListPojo;
import com.seehin.softwareclass.vo.ListVo;

import java.util.List;

/**
 * @Description [todo]
 * @Author Seehin
 * @Date 2020/5/7 11:18
 */
public interface ListService {

    List<String> getAllListNumber();

    List<ListPojo> getAllList();

    void disposeAffairs();

    void reduscePartQuantity(String listNumber,String pNumber,int outNumber);

    void disposeAffairExistOver(String pNumber,int quantity);

    ListPojo getInfoById(int id);

    int editSave(ListVo listVo);
}
