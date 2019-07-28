package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.base.entity.CrmSysMenu;
import com.base.service.AuthUserService;

@Controller
public class MyController {
	
	@Autowired
	AuthUserService aus;
	
	@RequestMapping(value = "/")
    public String index(){
        return "login";
    }
	
	@RequestMapping(value = "/work/work")
    public String work(){
        return "work/work";
    }
	@RequestMapping(value = "/work/add")
    public String workAdd(){
        return "work/add";
    }
	@RequestMapping(value = "/work/del")
    public String workDel(){
        return "work/del";
    }
	@RequestMapping(value = "/work/update")
    public String workUpdate(){
        return "work/update";
    }
	@RequestMapping(value = "/work/query")
    public String workQuery(){
        return "work/query";
    }
	@RequestMapping(value = "/base/base")
    public String base(){
        return "base/base";
    }
	@RequestMapping(value = "/base/add")
    public String baseAdd(){
        return "base/add";
    }
	@RequestMapping(value = "/base/del")
    public String baseDel(){
        return "base/del";
    }
	@RequestMapping(value = "/base/update")
    public String baseUpdate(){
        return "base/update";
    }
	@RequestMapping(value = "/base/query")
    public String baseQuery(){
        return "base/query";
    }
	@RequestMapping(value = "register")
	public String register() {
		return "register";
	}
	@RequestMapping(value = "toRegister")
	public String toRegister(String username,String password) {
		int insert = aus.insert(username, password);
		System.out.println(insert);
		return "login";
	}
	
	@RequestMapping(value = "/seccussUrl")
	public String seccussUrl(Model model) {
		User user = aus.getUser();
		List<CrmSysMenu> allMenu = aus.getAllMenu();
		model.addAttribute("user", user);
		model.addAttribute("menu", allMenu);
		return "seccussUrl";
	}
	
	@RequestMapping(value = "/logoutUrl")
	public String logoutUrl() {
		return "logout";
	}
	
}
