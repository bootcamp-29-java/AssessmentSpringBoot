/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootcamp.cobaspringapplication.controllers;

import com.bootcamp.cobaspringapplication.entities.Assessment;
import com.bootcamp.cobaspringapplication.entities.BatchClass;
import com.bootcamp.cobaspringapplication.entities.Participant;
import com.bootcamp.cobaspringapplication.repositories.EmployeeRepository;
import com.bootcamp.cobaspringapplication.services.IAssessmentDetailService;
import com.bootcamp.cobaspringapplication.services.IParticipantService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author ASUS
 */
@Controller
public class AssessmentDetailViewController {
    
    @Autowired
    IParticipantService ips;
    @Autowired
    IAssessmentDetailService iads;
    @Autowired
    EmployeeRepository er;
    
    @RequestMapping("/trainerpage/AssessmentView")
    public String showAssessment(Assessment assessment, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        String trainerId = er.getByEmail(user.getUsername()).getId();
        List<Participant> participants = new ArrayList<>();
        for (Participant p : ips.getAll()) {
            if (p.getBatchClass().getTrainer().getId().equals(trainerId)) {
                participants.add(p);
            }
        }
        model.addAttribute("participants", participants);
        return "/trainerpage/AssessmentView";
    }
    
    @RequestMapping("/trainerpage/AssessmentDetailView")
    public String showDetail(Assessment assessment, Model model) {
        model.addAttribute("assessmentdetails", iads.getAll());
        return "/trainerpage/AssessmentDetailView";
    }
}
