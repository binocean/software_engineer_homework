package com.seehin.softwareclass.service.impl;

import com.seehin.softwareclass.entity.SupplierPojo;
import com.seehin.softwareclass.mapper.SupplierMapper;
import com.seehin.softwareclass.service.SupplierService;
import com.seehin.softwareclass.vo.SupplierVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description [todo]
 * @Author Seehin
 * @Date 2020/5/6 14:54
 */
@Service
public class SupplierServiceImpl implements SupplierService {

    @Resource
    SupplierMapper supplierMapper;

    @Override
    public List<SupplierPojo> getAll() {
        return supplierMapper.getAllSupplier();
    }

    @Override
    public int addSupplier(SupplierVo supplierVo) {
        //
        SupplierPojo supplierPojo = new SupplierPojo();

        //先判断所传过来的供应商的名称是否存在
        int exist = supplierMapper.getCountOfName(supplierVo.getName());
        if (exist != 0){
            //说明该名称已经被占用
            return 1;
        }

        //数据转换
        supplierPojo.setName(supplierVo.getName());
        supplierPojo.setPhone(supplierVo.getPhone());
        supplierPojo.setAddress(supplierVo.getAddress());

        supplierMapper.addSupplier(supplierPojo);
        return 0;
    }

    @Override
    public SupplierPojo getOneSupplier(Integer id) {
        return supplierMapper.getOneSupplier(id);
    }

    @Override
    public void editSave(SupplierPojo supplierPojo) {
        supplierMapper.editSupplier(supplierPojo);
    }

    @Override
    public void delete(Integer id) {
        supplierMapper.deleteSupplier(id);
    }
}
