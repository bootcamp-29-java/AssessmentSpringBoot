/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootcamp.cobaspringapplication.controllers;

import com.bootcamp.cobaspringapplication.entities.Assessment;
import com.bootcamp.cobaspringapplication.entities.AssessmentDetail;
import com.bootcamp.cobaspringapplication.entities.BatchClass;
import com.bootcamp.cobaspringapplication.entities.Participant;
import com.bootcamp.cobaspringapplication.repositories.EmployeeRepository;
import com.bootcamp.cobaspringapplication.services.IAssessmentDetailService;
import com.bootcamp.cobaspringapplication.services.IAssessmentService;
import com.bootcamp.cobaspringapplication.services.ILessonCriteriaService;
import com.bootcamp.cobaspringapplication.services.IParticipantService;
import com.bootcamp.cobaspringapplication.services.ISylabusService;
import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
    @Autowired
    IAssessmentService ias;
    @Autowired
    ISylabusService iss;
    @Autowired
    ILessonCriteriaService ilcs;
    
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
    public String showDetail(AssessmentDetail assessmentDetail, Model model) {
        model.addAttribute("assessmentdetails", iads.getAll());
        return "/trainerpage/AssessmentDetailView";
    }
    
//    admin assessment
    @RequestMapping("/adminpage/AssessmentView")
    public String showAssessmentAdmin(Assessment assessment, Model model) {
        model.addAttribute("assessments", ias.getAll());
        model.addAttribute("participants", ips.getAll());
        model.addAttribute("sylabuses", iss.getAll());
        return "/adminpage/AssessmentView";
    }
    
    @GetMapping("/deleteAssessment")
    public String deleteAssessmentAdmin(String id, RedirectAttributes redirectAttributes) {
        try {
            ias.delete(id);
            redirectAttributes.addFlashAttribute("status", "Data Berhasil Dihapus");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("status", "Data Gagal Dihapus");
        }
        return "redirect:/adminpage/AssessmentView";
    }

    @GetMapping("/editAssessment")
    public String updateAssessmentAdmin(Assessment assessment, Model model) {
        model.addAttribute("assessments", ias.getAll());
        model.addAttribute("participants", ips.getAll());
        model.addAttribute("sylabuses", iss.getAll());
        return "/adminpage/AssessmentView";
    }
    
    @PostMapping("/inputAssessment")
    public String inputAssessmentAdmin(@Valid Assessment assessment, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("status", "Data Gagal Disimpan");
        } else {
            redirectAttributes.addFlashAttribute("status", "Data Berhasil Disimpan");
            ias.save(assessment);
        }
        return "redirect:/adminpage/AssessmentView";
    }
    
//    admin assessment detail
    @RequestMapping("/adminpage/AssessmentDetailView")
    public String showAssessmentDetailAdmin(AssessmentDetail assessmentDetail, Model model) {
        model.addAttribute("assessmentDetails", iads.getAll());
        model.addAttribute("lessonCriterias", ilcs.getAll());
        model.addAttribute("assessments", ias.getAll());
        return "/adminpage/AssessmentDetailView";
    }
    
    @GetMapping("/deleteAssessmentDetail")
    public String deleteAssessmentDetailAdmin(String id, RedirectAttributes redirectAttributes) {
        try {
            iads.delete(id);
            redirectAttributes.addFlashAttribute("status", "Data Berhasil Dihapus");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("status", "Data Gagal Dihapus");
        }
        return "redirect:/adminpage/AssessmentDetailView";
    }

    @GetMapping("/editAssessmentDetail")
    public String updateAssessmentDetailAdmin(AssessmentDetail assessmentDetail, Model model) {
        model.addAttribute("assessmentDetails", iads.getAll());
        model.addAttribute("lessonCriterias", ilcs.getAll());
        model.addAttribute("assessments", ias.getAll());
        return "/adminpage/AssessmentDetailView";
    }
    
    @PostMapping("/inputAssessmentDetail")
    public String inputAssessmentDetailAdmin(@Valid AssessmentDetail assessmentDetail, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("status", "Data Gagal Disimpan");
        } else {
            redirectAttributes.addFlashAttribute("status", "Data Berhasil Disimpan");
            iads.save(assessmentDetail);
        }
        return "redirect:/adminpage/AssessmentDetailView";
    }
}
