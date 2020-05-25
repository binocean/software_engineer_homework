package com.seehin.softwareclass.service.impl;

import com.seehin.softwareclass.entity.ListPojo;
import com.seehin.softwareclass.entity.PartPojo;
import com.seehin.softwareclass.mapper.ListMapper;
import com.seehin.softwareclass.mapper.PartMapper;
import com.seehin.softwareclass.service.PartService;
import com.seehin.softwareclass.vo.PartVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Description [todo]
 * @Author Seehin
 * @Date 2020/5/5 14:38
 */
@Service
public class PartServiceImpl implements PartService {

    @Resource
    PartMapper partMapper;

    @Resource
    ListMapper listMapper;

    @Override
    public List<PartPojo> get(){
        List<PartPojo> list;
        list = partMapper.getAllPart();
        return list;
    }

    @Override
    public int newSave(PartVo partVo) {
        PartPojo partPojo = new PartPojo();

        //先判断该零件编号是否存在
        int exit = partMapper.getCountOfPNumber(partVo.getPNumber());
        if (exit != 0){
            //编号已经存在
            return 1;
        }
        //
        partPojo.setPNumber(partVo.getPNumber());
        partPojo.setPName(partVo.getPName());
        partPojo.setPPrice(partVo.getPPrice());
        partPojo.setMainSupplier(partVo.getMainSupplier());
        partPojo.setMinorSupplier(partVo.getMinorSupplier());
        //插入一条新的数据进入数据表
        partMapper.newSave(partPojo);

        //零件插入成功后自动生成对应的库存清单
        initListTableAfterNewPart(partVo.getPNumber());
        return 0;
    }

    @Override
    public PartPojo getOne(Integer id) {
        return partMapper.getOnePart(id);
    }

    @Override
    public void editSave(PartPojo partPojo) {
        partMapper.editSave(partPojo);
    }

    @Override
    public void delete(Integer id) {
        //根据id取出相应的pNumber
        String pNumber = partMapper.getPNumberById(id);
        //同时库存中的零件
        listMapper.deleteListWhenPartDelete(pNumber);
        //删除该零件
        partMapper.deletePart(id);
    }

    @Override
    public void initListTableAfterNewPart(String pNumber){
        //取出所有的库存编码
        List<String> listOfListNumbers = listMapper.selectAllListNumber();
        //new出一个ListPojo用于数据插入数据表ist_table
        ListPojo listPojo = new ListPojo();

        //通过遍历库存编码来插入数据,使得每个仓库都有这样的零件
        for (String listOfListNumber:listOfListNumbers) {
            //将数据转化到pojo中用于插入数据表
            listPojo.setListNumber(listOfListNumber);
            listPojo.setPNumber(pNumber);
            //这里的零件数量先写死，后期迭代再自定义输入
            listPojo.setPMax(200);
            listPojo.setPQuantity(100);
            listPojo.setPMin(5);
            listMapper.insertNewList(listPojo);
        }
    }

    @Override
    public List<String> getAllPartOfNumber(String content){
        return partMapper.getAllPNumber(content);
    }
}
