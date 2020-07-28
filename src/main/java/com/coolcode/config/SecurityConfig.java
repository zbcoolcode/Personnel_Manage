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

    //链式编程
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers("/userlogin").permitAll()
                .antMatchers("/admin/addpage").hasAnyAuthority( "super")
                .antMatchers("/**/addpage").hasAnyAuthority("admin", "super")
                .antMatchers("/**/add").hasAnyAuthority("admin", "super")
                .antMatchers("/admin/admins").hasAnyAuthority("admin", "super")
                .antMatchers("/emp/empsbycondition").hasAnyAuthority("admin", "super")
                .antMatchers("/emp/emps").hasAnyAuthority("admin", "super")
                .antMatchers("/dep/deps").hasAnyAuthority("admin", "super")
                .antMatchers("/job/jobs").hasAnyAuthority("admin","super")
                .antMatchers("/admin/admin").hasAnyAuthority("super")
                .antMatchers("/emp/emp").hasAnyAuthority( "super")
                .antMatchers("/dep/dep").hasAnyAuthority("super")
                .antMatchers("/job/job").hasAnyAuthority( "super")
                .antMatchers("/**/update").hasAnyAuthority("super")
                .antMatchers("/**/delete").hasAnyAuthority("super");
        http.formLogin().usernameParameter("user").passwordParameter("pwd")
                .loginPage("/userlogin")
                .successForwardUrl("/admin/admins");
        http.logout();

        http.csrf().disable();      //禁止使用跨站访问

    }
}


