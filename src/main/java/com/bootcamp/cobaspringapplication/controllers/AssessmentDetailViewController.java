/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootcamp.cobaspringapplication.controllers;

import com.bootcamp.cobaspringapplication.entities.Assessment;
import com.bootcamp.cobaspringapplication.services.IAssessmentDetailService;
import com.bootcamp.cobaspringapplication.services.IParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
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
    
    @RequestMapping("/trainerpage/AssessmentView")
    public String showAssessment(Assessment assessment, Model model) {
        model.addAttribute("participants", ips.getAll());
        return "/trainerpage/AssessmentView";
    }
    
    @RequestMapping("/trainerpage/AssessmentDetailView")
    public String showDetail(Assessment assessment, Model model) {
        model.addAttribute("assessmentdetails", iads.getAll());
        return "/trainerpage/AssessmentDetailView";
    }
}
