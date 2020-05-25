package com.seehin.softwareclass.controller;

import com.seehin.softwareclass.service.impl.AffairServiceImpl;
import com.seehin.softwareclass.service.impl.ListServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributesModelMap;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @Description [todo]
 * @Author Seehin
 * @Date 2020/5/5 13:00
 */
@Controller
@CrossOrigin
@RequestMapping("/user")
public class UserController {

    AffairServiceImpl affairService;
    @Autowired
    public void setAffairService(AffairServiceImpl affairService){
        this.affairService = affairService;
    }

    ListServiceImpl listService;
    @Autowired
    public void setListService(ListServiceImpl listService){
        this.listService = listService;
    }

    /**
     * 此处只做一个简单的登录页面，后期补上完整登录模块
     */
    /** 用户名字*/
    private final String name1 = "seehin";
    private final String name2 = "test";
    /** 用户密码*/
    private final String pswd1 = "123456";
    private final String pswd2 = "1234";

    @PostMapping("/login")
    public String login(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            Map<String,String> map,
            HttpSession httpSession
    ){
        //初级判空
        if(StringUtils.isEmpty(username)||StringUtils.isEmpty(password)){
            //登录失败
            return "login";
        }
        if (name1.equals(username)&&pswd1.equals(password)){
            //成功---重定向到dashboard.html
            httpSession.setAttribute("loginName",username);
            return "redirect:/user/getAll";
        }
        if (name2.equals(username)&&pswd2.equals(password)){
            //成功---重定向到dashboard.html
            httpSession.setAttribute("loginName",username);
            return "redirect:/user/getAll";
        }
        map.put("msg","账号或者密码不正确");
        return "login";
    }

    @GetMapping("/loginOut")
    public String out(HttpSession httpSession){
        //httpSession.removeAttribute("loginName");
        httpSession.invalidate();
        return "login";
    }

    @GetMapping("/getAll")
    public String getAll(Model model){
        model.addAttribute("lists",listService.getAllList());
        return "dashboard";
    }
}
