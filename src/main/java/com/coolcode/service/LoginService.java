package com.coolcode.service;

import com.coolcode.bean.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @ClassName LoginService
 * @Author 小彬 bzcoolcode
 * @Date 2020/7/28 15:59
 * @Description LoginService
 * @Version 1.0
 */

@Configuration
public class LoginService implements UserDetailsService {
    
    @Autowired
    private AdminService adminService;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        System.out.println("用户名："+s);
        Admin admin = adminService.getAdmin(s);
        if(admin==null){
            return null;
        }
        String adminpassword = admin.getAdminpassword();

        String password = passwordEncoder.encode(adminpassword);
        System.out.println("密文"+password);

        String role=admin.getRole();
        System.out.println("role:"+role);


//        String password = passwordEncoder.encode("123456");
//        System.out.println("密文"+password);

        List<SimpleGrantedAuthority> authorities=authorities(admin.getRole());

        return new User(s,password,authorities);
    }

    public List<SimpleGrantedAuthority> authorities(String role){
        List<SimpleGrantedAuthority> authorities=new ArrayList<SimpleGrantedAuthority>();
        System.out.println(role);
        authorities.add(new SimpleGrantedAuthority(role));
        return authorities;
    }

}
