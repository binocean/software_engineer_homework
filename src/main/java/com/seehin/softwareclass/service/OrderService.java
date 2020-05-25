package com.seehin.softwareclass.service;

import com.seehin.softwareclass.entity.ExcelOfOrder;
import com.seehin.softwareclass.entity.OrderPojo;

import java.util.List;

/**
 * @Description [todo]
 * @Author Seehin
 * @Date 2020/5/9 20:13
 */
public interface OrderService {

    /**
     * 取出所有的订货信息
     * @return
     */
    List<OrderPojo> getAllOrder();

    /**
     * 导出订货信息
     * @return
     */
    List<ExcelOfOrder> tableToExcel();

    /**
     * 在导出excel后，零件自动入库
     */
    void autoInput();

    /**
     * 导出excel前自动将库存中需要的零件数量更新
     */
    void requiredQuantityOfPartBeforeOutToExcel();

    /**
     * 清空数据表
     */
    void truncateTableOfOrder();
}
