package com.seehin.softwareclass;

import com.seehin.softwareclass.mapper.ListMapper;
import com.seehin.softwareclass.mapper.PartMapper;
import com.seehin.softwareclass.mapper.SupplierMapper;
import com.seehin.softwareclass.vo.RequiredQuantityOfPartBeforeOutToExcelVo;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
class SoftwareClassApplicationTests {

    @Resource
    PartMapper partMapper;

    @Resource
    ListMapper listMapper;

    @Resource
    SupplierMapper supplierMapper;

    @Test
    void contextLoads() {
        System.out.println(partMapper.getAllPart());
    }

    @Test
    void test01(){
        List<Map<String,Object>> maps = supplierMapper.getAllPhoneOfSupplier();
        for (Map<String,Object> map: maps){
            System.out.println(map.get("id"));
            System.out.println(map.get("phone"));
        }
    }

    @Test
    void test02(){
        List<RequiredQuantityOfPartBeforeOutToExcelVo> list;

        list = listMapper.getRequiredQuantity();

        System.out.println(list);
    }

}
