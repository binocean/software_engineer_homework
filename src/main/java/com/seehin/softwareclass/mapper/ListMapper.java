package com.seehin.softwareclass.mapper;

import com.seehin.softwareclass.entity.ListPojo;
import com.seehin.softwareclass.vo.RequiredQuantityOfPartBeforeOutToExcelVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description [todo]
 * @Author Seehin
 * @Date 2020/5/7 11:12
 */
@Mapper
public interface ListMapper {

    /**
     * 调出所有的库存编号
     * @return
     */
    List<String> selectAllListNumber();

    /**
     * 查出事务要出库的零件的当前数量
     * @param listNumber
     * @param pNumber
     * @return int
     */
    ListPojo selectQuantity(@Param(value = "listNumber") String listNumber, @Param(value = "pNumber") String pNumber);

    /**
     *调取出所有的库存清单数据
     * @return
     */
    List<ListPojo> getAllList();

    /**
     * 向数据表中插入一条新数据
     * @param listPojo
     */
    void insertNewList(ListPojo listPojo);

    /**
     * 取出当前的零件数量
     * @param listNumber
     * @param pNumber
     * @return 当前数量
     */
    ListPojo getPQuantity(@Param(value = "listNumber") String listNumber,@Param(value = "pNumber") String pNumber);

    /**
     * 更新当前的零件数量
     * @param pQuantity
     * @param listNumber
     * @param pNumber
     */
    void updateQuantity(
            @Param(value = "pQuantity") int pQuantity,
            @Param(value = "listNumber") String listNumber,
            @Param(value = "pNumber") String pNumber);

    /**
     * 在零件被删除后，库存中的相应的零件也被删除
     * @param pNumber
     */
    void deleteListWhenPartDelete(@Param(value = "pNumber") String pNumber);

    /**
     *将库存中的当前零件的数量变为最大数量
     */
    void updateDataAfterAutoInput();

    /**
     * 取出库存中所需的零件数量
     * @return
     */
    List<RequiredQuantityOfPartBeforeOutToExcelVo> getRequiredQuantity();

    /**
     * 取出某个库存清单的信息
     * @param id
     * @return
     */
    ListPojo getInfo(@Param(value = "id") int id);

    /**
     * 根据id取出当前的库存数量
     * @param id
     * @return
     */
    int getPQuantityById(@Param(value = "id") Integer id);

    /**
     * 更新库存清单的阈值
     * @param max
     * @param min
     */
    void updateNumber(@Param(value = "id")int id,@Param(value = "max") int max,@Param(value = "min") int min);
}
