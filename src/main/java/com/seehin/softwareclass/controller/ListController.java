package com.seehin.softwareclass.controller;

import com.seehin.softwareclass.entity.ListPojo;
import com.seehin.softwareclass.service.impl.ListServiceImpl;
import com.seehin.softwareclass.vo.ListVo;
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
 * @Date 2020/5/9 1:00
 */
@Controller
@CrossOrigin
@RequestMapping("/list")
public class ListController {

    ListServiceImpl listService;
    @Autowired
    public void setListService(ListServiceImpl listService){
        this.listService = listService;
    }

    @GetMapping("/getAll")
    public String getAll(Model model, Map<String,String> map, HttpServletRequest request){
        model.addAttribute("lists",listService.getAllList());
        if (StringUtils.isEmpty(request.getSession().getAttribute("listNotExist"))){
            map.put("listNotExist",(String) request.getSession().getAttribute("listNotExist"));
            request.getSession().removeAttribute("listNotExist");
        }
        return "dashboard";
    }

    @GetMapping("/edit/{id}")
    public String edit(
            @PathVariable(name = "id")int id,
            Map<String,String> map,
            Model model,
            HttpSession session,
            HttpServletRequest request
    ){
        ListPojo listPojo = listService.getInfoById(id);
        if (listPojo.getId() == -1){
            session.setAttribute("listNotExist","该库存不存在");
            return "redirect:/list/getAll";
        }
        if (!StringUtils.isEmpty(request.getSession().getAttribute("numError"))){
            map.put("maxError",(String)request.getSession().getAttribute("maxError"));
            map.put("minError",(String)request.getSession().getAttribute("minError"));
            request.getSession().removeAttribute("numError");
            request.getSession().removeAttribute("minError");
            request.getSession().removeAttribute("maxError");
        }
        model.addAttribute("listPojo",listPojo);
        return "child/editList";
    }

    @PostMapping("/editSave")
    public String editSave(ListVo listVo,HttpSession session){
        int result = listService.editSave(listVo);
        if (result == 0){
            return "redirect:/list/getAll";
        }
        if (result == 1){
            session.setAttribute("numError","阈值出错");
            session.setAttribute("maxError","当前零件数量大于最大数量，请重新设置");
            return "redirect:/list/edit/"+listVo.getId();
        }
        session.setAttribute("numError","阈值出错");
        session.setAttribute("minError","当前零件数量小于临界值，请重新设置");
        return "redirect:/list/edit/"+listVo.getId();
    }
}
