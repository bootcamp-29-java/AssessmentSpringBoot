/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootcamp.cobaspringapplication.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *
 * @author ASUS
 */
@Configuration
public class WebConfig implements WebMvcConfigurer{
    
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/home").setViewName("index");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/adminpage/adminhome").setViewName("adminpage/adminhome");
        registry.addViewController("/adminpage/managebatchclass").setViewName("adminpage/managebatchclass");
        registry.addViewController("/adminpage/manageparticipant").setViewName("adminpage/manageparticipant");
        registry.addViewController("/trainerpage/trainderhome").setViewName("trainerpage/trainderhome");
    }
    
}
