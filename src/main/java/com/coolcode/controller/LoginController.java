package com.coolcode.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName LoginController
 * @Author 小彬 bzcoolcode
 * @Date 2020/7/28 9:21
 * @Description LoginController
 * @Version 1.0
 */

@Controller
public class LoginController {

    @RequestMapping("/userlogin")
    public String login(){
        return "login";
    }

    @RequestMapping("/")
    public String tologin(){
        return "login";
    }

    @RequestMapping("/403")
    public String error403(){
        return "403";
    }


}
