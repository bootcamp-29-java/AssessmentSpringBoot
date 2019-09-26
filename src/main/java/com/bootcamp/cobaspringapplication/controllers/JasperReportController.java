/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootcamp.cobaspringapplication.controllers;

import com.bootcamp.cobaspringapplication.services.IReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 *
 * @author ASUS
 */
@Controller
public class JasperReportController {

    @Autowired
    private IReportService reportService;
    @Autowired
    private JavaMailSender javaMailSender;

    @GetMapping("/testprint")
    public String generateReport(String id) {
        sendEmail();
        return reportService.generateReport(id);
    }
    
    void sendEmail() {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo("yosuak24@gmail.com");

        msg.setSubject("Testing from Spring Boot");
        msg.setText("Hello World \n Spring Boot Email");

        javaMailSender.send(msg);

    }
}
