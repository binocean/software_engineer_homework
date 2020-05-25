package com.seehin.softwareclass.controller;

import com.seehin.softwareclass.entity.PartPojo;
import com.seehin.softwareclass.service.impl.AffairServiceImpl;
import com.seehin.softwareclass.service.impl.ListServiceImpl;
import com.seehin.softwareclass.service.impl.PartServiceImpl;
import com.seehin.softwareclass.vo.AffairVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

/**
 * @Description [todo]
 * @Author Seehin
 * @Date 2020/5/7 1:13
 */
@Controller
@CrossOrigin
@RequestMapping("/affair")
public class AffairController {

    AffairServiceImpl affairService;
    @Autowired
    public void setAffairService(AffairServiceImpl affairService){
        this.affairService = affairService;
    }

    PartServiceImpl partService;
    @Autowired
    public void setPartService(PartServiceImpl partService){
        this.partService = partService;
    }

    ListServiceImpl listService;
    @Autowired
    public void setListService(ListServiceImpl listService){
        this.listService = listService;
    }

    @GetMapping("/getAll")
    public String getAll(Model model){

        model.addAttribute("affairs",affairService.getAllAffair());

        return "child/affair";
    }

    @GetMapping("/new")
    public String newAffair(Model model){
        //
        List<PartPojo> listOfPart = partService.get();
        List<String> listOfList = listService.getAllListNumber();

        model.addAttribute("parts",listOfPart);
        model.addAttribute("lists",listOfList);

        return "child/addAffair";
    }

    @PostMapping("/newSave")
    public String newSave(AffairVo affairVo, Map<String,String> mapOfHtml,Model model){
        //
        Map<String,String> map;
        //
        List<PartPojo> listOfPart = partService.get();
        List<String> listOfList = listService.getAllListNumber();

        map = affairService.insertOneAffair(affairVo);
        //判断结果
        if(map.get("msg").equals("success")){
            //成功
            return "redirect:/affair/getAll";
        }
        //
        model.addAttribute("parts",listOfPart);
        model.addAttribute("lists",listOfList);
        //
        System.out.println("最大数量："+map.get("msg"));
        mapOfHtml.put("maxOut",map.get("msg"));
        mapOfHtml.put("partNumber",affairVo.getPNumber());
        mapOfHtml.put("usedListNumber",affairVo.getListNumber());
        mapOfHtml.put("usedPNumber",affairVo.getPNumber());
        return "child/addAffair";
    }
}
