package com.seehin.softwareclass.service.impl;

import com.seehin.softwareclass.entity.ExcelOfOrder;
import com.seehin.softwareclass.entity.OrderPojo;
import com.seehin.softwareclass.mapper.ListMapper;
import com.seehin.softwareclass.mapper.OrderMapper;
import com.seehin.softwareclass.mapper.SupplierMapper;
import com.seehin.softwareclass.service.OrderService;
import com.seehin.softwareclass.vo.RequiredQuantityOfPartBeforeOutToExcelVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description [todo]
 * @Author Seehin
 * @Date 2020/5/9 20:13
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    OrderMapper orderMapper;

    @Resource
    SupplierMapper supplierMapper;

    @Resource
    ListMapper listMapper;

    ListServiceImpl listService;
    @Autowired
    public void setListService(ListServiceImpl listService){
        this.listService = listService;
    }


    @Override
    public List<OrderPojo> getAllOrder() {
        return orderMapper.getAllOrder();
    }

    @Override
    public List<ExcelOfOrder> tableToExcel(){
        //取到供应商的kv数据
        List<Map<String,Object>> list = supplierMapper.getAllPhoneOfSupplier();
        //取出订货信息的所有数据
        List<ExcelOfOrder> listOfExcelOfOrder = orderMapper.getAllToExcel();
        //用于存储解析后的供应商kv数据
        Map<String,String> mapOfPhone = new HashMap<>();

        //数据解析---将数据以<供应商编号，供应商电话>的kv型式存到mapOfPhone中
        for (Map<String,Object> map: list){
            mapOfPhone.put(map.get("id").toString(),map.get("phone").toString());
        }
        //填充Excel表缺少的数据
        for (ExcelOfOrder excelOfOrder:listOfExcelOfOrder){
            excelOfOrder.setMainPhone(mapOfPhone.get(excelOfOrder.getMainSupplier()));
            excelOfOrder.setMinorPhone(mapOfPhone.get(excelOfOrder.getMinorSupplier()));
        }
        return listOfExcelOfOrder;
    }

    @Override
    public void autoInput() {
        listMapper.updateDataAfterAutoInput();
    }

    /**
     * 在导出前，将库存中需要的零件的数量导入订单
     */
    @Override
    public void requiredQuantityOfPartBeforeOutToExcel(){
        List<RequiredQuantityOfPartBeforeOutToExcelVo> list;

        list = listMapper.getRequiredQuantity();
        System.out.println(list);
        for (RequiredQuantityOfPartBeforeOutToExcelVo a:list){
            listService.disposeAffairExistOver(a.getPNumber(),(a.getPMax()-a.getPQuantity()));
        }
    }

    @Override
    public void truncateTableOfOrder(){
        orderMapper.truncateTable();
    }
}
