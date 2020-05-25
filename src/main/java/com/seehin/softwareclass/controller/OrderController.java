package com.seehin.softwareclass.controller;

import com.seehin.softwareclass.entity.ExcelOfOrder;
import com.seehin.softwareclass.service.impl.OrderServiceImpl;
import com.seehin.softwareclass.utils.ExcelUtil;
import com.seehin.softwareclass.utils.GetTimeNowUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @Description [todo]
 * @Author Seehin
 * @Date 2020/5/9 20:07
 */
@Controller
@CrossOrigin
@RequestMapping("/order")
public class OrderController {

    OrderServiceImpl orderService;
    @Autowired
    public void setOrderService(OrderServiceImpl orderService){
        this.orderService = orderService;
    }

    @GetMapping("/getAll")
    public String getAll(Model model){
        model.addAttribute("orders",orderService.getAllOrder());
        return "child/order";
    }

    @GetMapping("/outToExcel")
    public void outToExcel(HttpServletResponse httpServletResponse){
        GetTimeNowUtil getTimeNowUtil = new GetTimeNowUtil();
        //取出所有的订货信息
        List<ExcelOfOrder> list = orderService.tableToExcel();
        System.out.println("导出报表："+list);
        //将库存中所需要的零件数量更新到订货信息中
        orderService.requiredQuantityOfPartBeforeOutToExcel();
        //自动入库 --- 模拟规则 --- 入库后当前零件数量==库中最大零件数量
        orderService.autoInput();

        ExcelUtil.exportExcel(
                list,
                "仓库管理系统自动生成订货报表",
                "sheet1",
                ExcelOfOrder.class,
                getTimeNowUtil.getDateOfString()+"订货报表.xls",
                httpServletResponse
        );

        //导出Excel后自动清空当天订货数据
        orderService.truncateTableOfOrder();
    }
}
