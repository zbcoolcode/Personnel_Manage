package com.coolcode.config;

import com.coolcode.dao.AdminDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

//AOP:拦截器
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    //默认密码不支持，使用加密方式
    @Bean
    public PasswordEncoder myPassworld(){
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private AdminDao adminDao;

    @Autowired
    private DataSource dataSource;



    //链式编程
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers( "/userlogin").permitAll()

                .antMatchers("/admin/admins").hasAnyAuthority("admin","super")
                .antMatchers("/admin/**").hasAnyAuthority("super")
                .antMatchers("/emp/emps").hasAnyAuthority("admin","super")
                .antMatchers("/emp/**").hasAnyAuthority("super")
                .antMatchers("/dep/deps").hasAnyAuthority("admin","super")
                .antMatchers("/dep/**").hasAnyAuthority("super")
                .antMatchers("/job/jobs").hasAnyAuthority("admin","super")
                .antMatchers("/job/**").hasAnyAuthority("super");

        http.formLogin().usernameParameter("user").passwordParameter("pwd")
                .loginPage("/userlogin")
                .successForwardUrl("/admin/admins");
        http.logout();

        http.csrf().disable();      //禁止使用跨站访问




//        //设置首页所有人可以访问，功能页面需要身份验证
//        http.authorizeRequests()
//                .antMatchers("/").permitAll()
//                .antMatchers("/emp/**").hasRole("admin")
//                .antMatchers("/emp/update").hasRole("superadmin")
//                .antMatchers("/dep/**").hasRole("admin")
//                .antMatchers("/dep/update").hasRole("superadmin")
//                .antMatchers("/job/**").hasRole("admin")
//                .antMatchers("/job/update").hasRole("superadmin");
//
//        http.formLogin().loginPage("/login").loginProcessingUrl("/admin/admins"); //访问没有权限页面要身份验证页面
//
//        http.csrf().disable();
//        http.rememberMe().rememberMeParameter("remember");
//        http.logout().logoutSuccessUrl("/");
    }

    //身份验证
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
////        auth.jdbcAuthentication().passwordEncoder(new BCryptPasswordEncoder())
////            .withUser("bzini").password(new BCryptPasswordEncoder().encode("bzini1")).roles("admin")
////            .and()
////            .withUser("ler").password(new BCryptPasswordEncoder().encode("ler1")).roles("vip1","vip2")
////            .and()
////            .withUser("root").password(new BCryptPasswordEncoder().encode("root1")).roles("vip1","vip2","vip3");
////        auth.jdbcAuthentication().dataSource(dataSource)
////                .withUser(adminDao.getAdminById());
//    }
}
