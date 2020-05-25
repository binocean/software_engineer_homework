package com.seehin.softwareclass.controller;

import com.seehin.softwareclass.entity.PartPojo;
import com.seehin.softwareclass.entity.SupplierPojo;
import com.seehin.softwareclass.service.impl.PartServiceImpl;
import com.seehin.softwareclass.service.impl.SupplierServiceImpl;
import com.seehin.softwareclass.vo.PartVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * @Description [todo]
 * @Author Seehin
 * @Date 2020/5/5 12:57
 */
@Controller
@CrossOrigin
@RequestMapping("/part")
public class PartController {

    PartServiceImpl partServiceImpl;

    @Autowired
    public void setPartServiceImpl(PartServiceImpl partServiceImpl){
        this.partServiceImpl = partServiceImpl;
    }

    SupplierServiceImpl supplierService;
    @Autowired
    public void setSupplierService(SupplierServiceImpl supplierService){
        this.supplierService = supplierService;
    }


    @GetMapping("/getAll")
    public String get(Model model){
        List<PartPojo> list = partServiceImpl.get();
        model.addAttribute("partList",list);
        return "child/list";
    }

    @GetMapping("/new")
    public String newPart(Model model, HttpServletRequest request, Map<String,String> map){
        List<SupplierPojo> list = supplierService.getAll();
        model.addAttribute("suppliers",list);
        if (!StringUtils.isEmpty(request.getSession().getAttribute("newPart"))){
            map.put("pNumberExist", (String) request.getSession().getAttribute("newPart"));
            request.getSession().removeAttribute("newPart");
        }
        return "child/add";
    }

    @PostMapping("newSave")
    public String newSave(PartVo partVo, HttpSession session){
        int result = partServiceImpl.newSave(partVo);
        if (result == 1){
            session.setAttribute("newPart","编号已经存在");
            return "redirect:/part/new";
        }
        return "redirect:/part/getAll";
    }

    @GetMapping("/edit/{id}")
    public String editMsg(@PathVariable("id") Integer id, Model model){
        model.addAttribute("part",partServiceImpl.getOne(id));
        model.addAttribute("suppliers",supplierService.getAll());
        return "child/edit";
    }

    @PostMapping("/editSave")
    public String editSave(PartPojo partPojo){
        System.out.println(partPojo.toString());
        partServiceImpl.editSave(partPojo);
        return "redirect:/part/getAll";
    }

    @PostMapping("/delete/{id}")
    public String deletePart(@PathVariable("id") Integer id){
        partServiceImpl.delete(id);
        return "redirect:/part/getAll";
    }

    @ResponseBody
    @GetMapping("/search")
    public List<String> search(@RequestParam(name = "content") String content){
        return partServiceImpl.getAllPartOfNumber(content);
    }

}
