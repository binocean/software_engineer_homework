package com.seehin.softwareclass.service.impl;

import com.seehin.softwareclass.entity.AffairPojo;
import com.seehin.softwareclass.entity.ListPojo;
import com.seehin.softwareclass.entity.OrderPojo;
import com.seehin.softwareclass.entity.PartPojo;
import com.seehin.softwareclass.mapper.AffairMapper;
import com.seehin.softwareclass.mapper.ListMapper;
import com.seehin.softwareclass.mapper.OrderMapper;
import com.seehin.softwareclass.mapper.PartMapper;
import com.seehin.softwareclass.service.ListService;
import com.seehin.softwareclass.utils.GetTimeNowUtil;
import com.seehin.softwareclass.vo.ListVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.List;

/**
 * @Description [todo]
 * @Author Seehin
 * @Date 2020/5/7 11:19
 */
@Service
public class ListServiceImpl implements ListService {

    @Resource
    ListMapper listMapper;

    @Resource
    AffairMapper affairMapper;

    @Resource
    OrderMapper orderMapper;

    @Resource
    PartMapper partMapper;

    /** 定义logback+slf4j日志*/
    private final Logger logger = LoggerFactory.getLogger(ListServiceImpl.class);


    @Override
    public List<String> getAllListNumber() {
        return listMapper.selectAllListNumber();
    }

    @Override
    public List<ListPojo> getAllList() {
        return listMapper.getAllList();
    }

    /**
     * 处理未处理过的所有事务
     */
    @Override
    public void disposeAffairs(){
        //取出未处理的事务清单
        List<AffairPojo> list = affairMapper.getAllNotDisposeAffair();

        //利用reduscePartQuantity方法接管对事务的处理
        for(AffairPojo affairPojo:list){
            System.out.println(affairPojo.toString());
            //对事务指定的库存零件的数量进行减少操作，两种情况：1 减少数量成功；2 零件数量不够出库，因此减少失败
            reduscePartQuantity(affairPojo.getListNumber(),affairPojo.getPNumber(),affairPojo.getOutNumber());
            affairMapper.updateDispose(affairPojo.getId());
        }
    }

    /**
     * 该方法负责处理具体对某一条事务进行处理
     * @param listNumber
     * @param pNumber
     * @param outNumber
     */
    @Override
    public void reduscePartQuantity(String listNumber,String pNumber,int outNumber){
        //首先取出当前的零件数量
        ListPojo listPojo = listMapper.getPQuantity(listNumber,pNumber);

        if ((listPojo.getPQuantity()-listPojo.getPMin())>=outNumber){
            //正常减少
            listMapper.updateQuantity(listPojo.getPQuantity()-outNumber,listNumber,pNumber);
        }else{
            //出库数量超出库存数量,输出欠缺的数量到订货表中
            disposeAffairExistOver(pNumber,outNumber);
        }
    }

    /**
     * 该方法主要负责处理某个事务后对订货表的更新
     * @param pNumber
     * @param quantity
     */
    @Override
    public void disposeAffairExistOver(String pNumber,int quantity){
        //取出所有的订货表中的零件编号
        int existOrNot= orderMapper.pNumberExistOrNot(pNumber);
        OrderPojo orderPojo = new OrderPojo();

        //
        if (existOrNot == 0){
            //说明该零件的订货消息不存在
            GetTimeNowUtil getTimeNowUtil = new GetTimeNowUtil();
            Timestamp timestamp = getTimeNowUtil.getTimestamp();
            logger.info("ListServiceImpl->diposeAffairExistOver时间定义[1]："+timestamp);
            //封装对应的orderPojo
            PartPojo partPojo = partMapper.getOnePartByPNumber(pNumber);
            orderPojo.setPNumber(partPojo.getPNumber());
            orderPojo.setPName(partPojo.getPName());
            orderPojo.setMainSupplier(partPojo.getMainSupplier());
            orderPojo.setMinorSupplier(partPojo.getMinorSupplier());
            orderPojo.setInNumber(quantity);
            orderPojo.setCreatetime(timestamp);
            //插入
            orderMapper.insertNewOrder(orderPojo);
        }else{
            //说明该数据已经存在了，那么直接修改数据(增加该零件的订货数量)即可
            GetTimeNowUtil getTimeNowUtil = new GetTimeNowUtil();
            Timestamp timestamp = getTimeNowUtil.getTimestamp();
            logger.info("ListServiceImpl->diposeAffairExistOver时间定义[2]："+timestamp);
            //取出该零件的目前的订货数量
            int inNumber = orderMapper.getNowInNumber(pNumber);
            inNumber += quantity;
            //更新数据
            orderMapper.updateInNumber(pNumber,inNumber,timestamp);
        }
    }

    @Override
    public ListPojo getInfoById(int id) {
        ListPojo listPojo;

        listPojo = listMapper.getInfo(id);
        if (listPojo == null){
            return new ListPojo(-1);
        }
        System.out.println(listPojo.toString());
        return listPojo;
    }

    @Override
    public int editSave(ListVo listVo) {
        int num;
        System.out.println(listVo.toString());
        num = listMapper.getPQuantityById(listVo.getId());
        if (num<=listVo.getPMax()&&num>=listVo.getPMin()){
            listMapper.updateNumber(listVo.getId(),listVo.getPMax(),listVo.getPMin());
            return 0;
        }
        if (num>listVo.getPMax()){
            return 1;
        }else {
            return -1;
        }
    }
}
