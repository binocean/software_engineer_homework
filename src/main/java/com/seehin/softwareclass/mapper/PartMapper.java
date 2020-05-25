package com.seehin.softwareclass.mapper;

import com.seehin.softwareclass.entity.PartPojo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description [todo]
 * @Author Seehin
 * @Date 2020/5/5 14:42
 */
@Mapper
public interface PartMapper {

    /**
     *取出所有的零件信息
     * @return 集合
     */
    List<PartPojo> getAllPart();

    /**
     * 保存新增的零件信息
     * @param partPojo
     */
    void newSave(PartPojo partPojo);

    /**
     * 根据id取出零件信息
     * @param id
     * @return
     */
    PartPojo getOnePart(Integer id);

    /**
     * 更新零件的数据
     * @param partPojo
     */
    void editSave(PartPojo partPojo);

    /**
     * 删除零件
     * @param id
     */
    void deletePart(Integer id);

    /**
     * 根据零件的编号取出该条零件的数据
     * @param pNumber
     * @return
     */
    PartPojo getOnePartByPNumber(@Param(value = "pNumber") String pNumber);

    /**
     * 根据id查找出该零件的p_number
     * @param id
     * @return
     */
    String getPNumberById(@Param(value = "id") Integer id);

    /**
     * 模糊搜索
     * @param content
     * @return
     */
    List<String> getAllPNumber(@Param(value = "content") String content);

    /**
     * 查看某个零件编号是否存在
     * @param pNumber
     * @return
     */
    int getCountOfPNumber(String pNumber);
}
