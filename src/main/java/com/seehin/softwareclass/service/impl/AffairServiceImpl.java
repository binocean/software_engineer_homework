package com.seehin.softwareclass.service.impl;

import com.seehin.softwareclass.entity.AffairPojo;
import com.seehin.softwareclass.entity.ListPojo;
import com.seehin.softwareclass.mapper.AffairMapper;
import com.seehin.softwareclass.mapper.ListMapper;
import com.seehin.softwareclass.service.AffairService;
import com.seehin.softwareclass.vo.AffairVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description [todo]
 * @Author Seehin
 * @Date 2020/5/7 1:20
 */
@Service
public class AffairServiceImpl implements AffairService {

    @Resource
    AffairMapper affairMapper;

    @Resource
    ListMapper listMapper;

    @Override
    public List<AffairPojo> getAllAffair() {
        return affairMapper.getAll();
    }

    @Override
    public Map<String,String> insertOneAffair(AffairVo affairVo) {
        //
        int result;
        //
        AffairPojo affairPojo = new AffairPojo();
        //
        Date date = new Date();
        Timestamp timestamp = new Timestamp(date.getTime());
        //
        String time = timestamp.toString().substring(0,timestamp.toString().indexOf("."));
        timestamp = Timestamp.valueOf(time);
        //
        Map<String,String> map = new HashMap<>();

        result = judgeEnough(affairVo);
        //如果出库数量超过库存零件数量
        if (result <0){
            result = result+Integer.parseInt(affairVo.getOutNumber());
            map.put("msg",String.valueOf(result));
            return map;
        }
        //出库数量没有超过库存数量
        affairPojo.setListNumber(affairVo.getListNumber());
        affairPojo.setPNumber(affairVo.getPNumber());
        affairPojo.setOutNumber(Integer.parseInt(affairVo.getOutNumber()));
        affairPojo.setCreatetime(timestamp);
        affairPojo.setDispose(0);
        newSave(affairPojo);
        map.put("msg","success");
        return map;
    }

    @Override
    public void newSave(AffairPojo affairPojo) {
        affairMapper.newSave(affairPojo);
    }

    @Override
    public int judgeEnough(AffairVo affairVo) {
        ListPojo listPojo = new ListPojo();
        //
        listPojo = listMapper.selectQuantity(affairVo.getListNumber(),affairVo.getPNumber());

        return listPojo.getPQuantity()-listPojo.getPMin()-Integer.parseInt(affairVo.getOutNumber());
    }
}
