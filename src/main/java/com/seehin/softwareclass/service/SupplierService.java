package com.seehin.softwareclass.service;

import com.seehin.softwareclass.entity.SupplierPojo;
import com.seehin.softwareclass.vo.SupplierVo;

import java.util.List;

/**
 * @Description [todo]
 * @Author Seehin
 * @Date 2020/5/6 14:53
 */
public interface SupplierService {

    List<SupplierPojo> getAll();

    int addSupplier(SupplierVo supplierVo);

    SupplierPojo getOneSupplier(Integer id);

    void editSave(SupplierPojo supplierPojo);

    void delete(Integer id);
}
