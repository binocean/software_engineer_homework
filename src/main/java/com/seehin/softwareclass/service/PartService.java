package com.seehin.softwareclass.service;

import com.seehin.softwareclass.entity.PartPojo;
import com.seehin.softwareclass.vo.PartVo;

import java.util.List;

/**
 * @Description 有关零件的业务逻辑处理
 * @Author Seehin
 * @Date 2020/5/5 14:37
 */
public interface PartService {

    List<PartPojo> get();

    int newSave(PartVo partVo);

    PartPojo getOne(Integer id);

    void editSave(PartPojo partPojo);

    void delete(Integer id);

    void initListTableAfterNewPart(String pNumber);

    List<String> getAllPartOfNumber(String content);
}
