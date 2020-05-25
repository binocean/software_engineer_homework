package com.seehin.softwareclass.controller;

import com.seehin.softwareclass.entity.SupplierPojo;
import com.seehin.softwareclass.service.impl.SupplierServiceImpl;
import com.seehin.softwareclass.vo.SupplierVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;


/**
 * @Description [todo]
 * @Author Seehin
 * @Date 2020/5/6 21:42
 */
@Controller
@CrossOrigin
@RequestMapping("/supplier")
public class SupplierController {

    SupplierServiceImpl supplierService;
    @Autowired
    public void setSupplierService(SupplierServiceImpl supplierService){
        this.supplierService = supplierService;
    }

    @GetMapping("/getAll")
    public String getAll(Model model){
        model.addAttribute("suppliers",supplierService.getAll());
        return "child/supplier";
    }

    @GetMapping("/new")
    public String newSupplier(Map<String,String> map,HttpServletRequest request){
        if (!StringUtils.isEmpty(request.getSession().getAttribute("supplierNameExist"))){
            map.put("supplierNameExist",(String) request.getSession().getAttribute("supplierNameExist"));
            request.getSession().removeAttribute("supplierNameExist");
        }
        return "child/addSupplier";
    }

    @PostMapping("/newSave")
    public String newSave(SupplierVo supplierVo, HttpSession session){
        int result = supplierService.addSupplier(supplierVo);
        if (result == 1){
            session.setAttribute("supplierNameExist","该供应商名称已经被占用");
            return "redirect:/supplier/new";
        }
        return "redirect:/supplier/getAll";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id,Model model){
        model.addAttribute("supplier",supplierService.getOneSupplier(id));
        return "child/editSupplier";
    }

    @PostMapping("/editSave")
    public String editSave(SupplierPojo supplierPojo){
        supplierService.editSave(supplierPojo);
        return "redirect:/supplier/getAll";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id){
        supplierService.delete(id);
        return "redirect:/supplier/getAll";
    }
}
