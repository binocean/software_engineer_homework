package com.seehin.softwareclass.service;

import com.seehin.softwareclass.entity.AffairPojo;
import com.seehin.softwareclass.vo.AffairVo;

import java.util.List;
import java.util.Map;

/**
 * @Description [todo]
 * @Author Seehin
 * @Date 2020/5/7 1:19
 */
public interface AffairService {

    List<AffairPojo> getAllAffair();

    Map<String,String> insertOneAffair(AffairVo affairVo);

    void newSave(AffairPojo affairPojo);

    int judgeEnough(AffairVo affairVo);
}
