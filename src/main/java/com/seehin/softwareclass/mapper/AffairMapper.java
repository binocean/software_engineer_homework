package com.seehin.softwareclass.mapper;

import com.seehin.softwareclass.entity.AffairPojo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Description [todo]
 * @Author Seehin
 * @Date 2020/5/7 1:16
 */
@Mapper
public interface AffairMapper {

    /**
     * 调取出所有的事务信息
     * @return 集合
     */
    List<AffairPojo> getAll();

    /**
     * 插入一条新的数据
     * @param affairPojo
     */
    void newSave(AffairPojo affairPojo);

    /**
     * 将所有的未处理的事务行数据
     * @return 集合
     */
    List<AffairPojo> getAllNotDisposeAffair();

    /**
     * 将处理过的事务置1
     * @param id
     */
    void updateDispose(@Param(value = "id") Integer id);

}
