package com.seehin.softwareclass.mapper;

import com.seehin.softwareclass.entity.SupplierPojo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @Description 供应商信息表
 * @Author Seehin
 * @Date 2020/5/6 14:48
 */
@Mapper
public interface SupplierMapper {

    /**
     * 取出所有的供应商信息
     * @return 供应商集合
     */
    List<SupplierPojo> getAllSupplier();

    /**
     * 根据Id取出某个对应的供应商
     * @param id
     * @return 一个SupplierPojo对象
     */
    SupplierPojo getOneSupplier(Integer id);

    /**
     * 增加一个新的供应商
     * @param supplierPojo
     */
    void addSupplier(SupplierPojo supplierPojo);

    /**
     * 修改某个供应商的信息
     * @param supplierPojo
     */
    void editSupplier(SupplierPojo supplierPojo);

    /**
     * 删除某个供应商
     * @param id
     */
    void deleteSupplier(Integer id);

    /**
     * 取出所有的商家的编号对应电话
     * @return
     */
    List<Map<String,Object>> getAllPhoneOfSupplier();

    /**
     * 根据供应商名称查出是否已经被占用
     * @param name
     * @return
     */
    int getCountOfName(@Param(value = "name") String name);
}
