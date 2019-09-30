/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootcamp.cobaspringapplication.controllers;

import com.bootcamp.cobaspringapplication.entities.Employee;
import com.bootcamp.cobaspringapplication.services.IEmployeeService;
import com.bootcamp.cobaspringapplication.services.IReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author ASUS
 */
@Controller
public class JasperReportController {

    @Autowired
    private IReportService reportService;
    @Autowired
    private IEmployeeService ies;
    @Autowired
    private JavaMailSender javaMailSender;

    @PostMapping("/testprint")
    public String generateReport(@ModelAttribute("id") String id, @ModelAttribute("grade") String grade) {
        Employee employee = ies.getById(id);
        employee.getParticipant().setGrade(grade);
        ies.save(employee);
        sendEmail(employee.getEmail(), employee.getParticipant().getGrade(), employee.getFirstName());
        return reportService.generateReport(id);
    }
    
    void sendEmail(String email, String grade, String firstName) {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(email);

        msg.setSubject("Hei, " + firstName + "! Nilai kamu sudah keluar!");
        msg.setText("Selamat! Grade kamu adalah " + grade);

        javaMailSender.send(msg);

    }
}
