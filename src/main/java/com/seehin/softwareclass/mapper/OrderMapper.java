package com.seehin.softwareclass.mapper;

import com.seehin.softwareclass.entity.ExcelOfOrder;
import com.seehin.softwareclass.entity.OrderPojo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.sql.Timestamp;
import java.util.List;

/**
 * @Description [todo]
 * @Author Seehin
 * @Date 2020/5/9 20:08
 */
@Mapper
public interface OrderMapper {

    /**
     * 取出所有的订单信息
     * @return
     */
    List<OrderPojo> getAllOrder();

    /**
     * 查看订货表中是否存在该条零件的订货数据
     * @param pNumber
     * @return 1 or 0
     */
    int pNumberExistOrNot(@Param(value = "pNumber") String pNumber);

    /**
     * 插入一条新的订货信息进入订货表中
     * @param orderPojo
     */
    void insertNewOrder(OrderPojo orderPojo);

    /**
     *
     * @param pNumber
     * @return
     */
    int getNowInNumber(@Param(value = "pNumber") String pNumber);

    /**
     * 更新订货数量
     * @param pNumber
     * @param inNumber
     */
    void updateInNumber(
            @Param(value = "pNumber") String pNumber,
            @Param(value = "inNumber") int inNumber,
            @Param(value = "createtime") Timestamp timestamp
            );

    /**
     * 将订货信息都导出来
     * @return
     */
    List<ExcelOfOrder> getAllToExcel();

    /**
     * 清空表数据
     */
    void truncateTable();
}
