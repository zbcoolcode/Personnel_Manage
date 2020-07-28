package com.coolcode.controller;

import com.coolcode.bean.Admin;
import com.coolcode.bean.Department;
import com.coolcode.bean.Employee;
import com.coolcode.bean.Job;
import com.coolcode.service.AdminService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.annotations.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @ClassName AdminController
 * @Author 小彬 bzcoolcode
 * @Date 2020/7/27 21:43
 * @Description AdminController
 * @Version 1.0
 */

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @RequestMapping("/admins")
    public ModelAndView getAdmins(@RequestParam(value = "pn", defaultValue = "1") Integer pn){
        ModelAndView mv = new ModelAndView();
        PageHelper.startPage(pn, 8);
        List<Admin> admins = adminService.getAdmins();
        System.out.println(admins);
        PageInfo<Admin>  adminPageInfo= new PageInfo<Admin>(admins);
        mv.addObject("adminPageInfo",adminPageInfo);
        mv.setViewName("admin-list");
        return mv;
    }


    @RequestMapping("/admin")
    public String getAdmin(String adminid,Model model){
        Admin admin = adminService.getAdmin(adminid);
        model.addAttribute("admin",admin);
        return "admin-update";
    }

    @RequestMapping("/addpage")
    public String addpage(){
        return "admin-add";
    }

    @RequestMapping("/add")
    public String addAdmin(Admin admin){
        adminService.addAdmin(admin);
        return "redirect:/admin/admins";
    }

    @RequestMapping("/update")
    public String updateAdmin(Admin admin){
        System.out.println(admin);
        adminService.updateAdmin(admin);
        return "redirect:/admin/admins";
    }

    @RequestMapping("/delete")
    public String deleteAdmin(Integer adminid){
        adminService.deleteAdmin(adminid);
        return "redirect:/admin/admins";
    }

}
