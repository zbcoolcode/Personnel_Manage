package com.coolcode.service;

import com.coolcode.bean.Admin;
import com.coolcode.dao.AdminDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @ClassName AdminService
 * @Author 小彬 bzcoolcode
 * @Date 2020/7/27 21:42
 * @Description AdminService
 * @Version 1.0
 */
@Service
public class AdminService{
    @Autowired
    private AdminDao adminDao;



    public List<Admin> getAdmins(){
        List<Admin> admins = adminDao.getAdmins();
        return admins;

    }


    public Admin getAdmin(String adminid){
        Admin admin= adminDao.getAdminById(adminid);
        return admin;

    }


    public int updateAdmin(Admin admin){
        int i= adminDao.updateAdmin(admin);
        return i;

    }


    public int deleteAdmin(Integer adminid){
        int i= adminDao.deleteAdmin(adminid);
        return i;
    }

    public int addAdmin(Admin admin) {
        return adminDao.addAdmin(admin);
    }



}
