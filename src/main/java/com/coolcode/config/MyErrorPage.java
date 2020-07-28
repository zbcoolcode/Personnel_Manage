package com.coolcode.config;

import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

/**
 * @ClassName MyErrorPage
 * @Author 小彬 bzcoolcode
 * @Date 2020/7/28 14:23
 * @Description MyErrorPage
 * @Version 1.0
 */

@Configuration
public class MyErrorPage implements ErrorPageRegistrar {

    //定制错误跳转页面
    @Override
    public void registerErrorPages(ErrorPageRegistry registry) {
        ErrorPage error403Page = new ErrorPage(HttpStatus.FORBIDDEN, "/403");
        ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/404");
        registry.addErrorPages(error403Page,error404Page);
    }


}
