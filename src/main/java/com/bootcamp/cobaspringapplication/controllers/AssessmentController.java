/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootcamp.cobaspringapplication.controllers;

import com.bootcamp.cobaspringapplication.entities.Assessment;
import com.bootcamp.cobaspringapplication.entities.AssessmentDetail;
import com.bootcamp.cobaspringapplication.entities.BatchClass;
import com.bootcamp.cobaspringapplication.entities.LessonCriteria;
import com.bootcamp.cobaspringapplication.entities.Participant;
import com.bootcamp.cobaspringapplication.entities.Sylabus;
import com.bootcamp.cobaspringapplication.repositories.EmployeeRepository;
import com.bootcamp.cobaspringapplication.services.IAssessmentDetailService;
import com.bootcamp.cobaspringapplication.services.IAssessmentService;
import com.bootcamp.cobaspringapplication.services.IBatchClassService;
import com.bootcamp.cobaspringapplication.services.IBatchService;
import com.bootcamp.cobaspringapplication.services.ISylabusService;
import com.bootcamp.cobaspringapplication.services.IParticipantService;
import com.bootcamp.cobaspringapplication.services.IClassesService;
import com.bootcamp.cobaspringapplication.services.ICriteriaService;
import com.bootcamp.cobaspringapplication.services.IEmployeeRoleService;
import com.bootcamp.cobaspringapplication.services.IEmployeeService;
import com.bootcamp.cobaspringapplication.services.ILessonCriteriaService;
import com.bootcamp.cobaspringapplication.services.ILessonService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author arman
 */
@Controller
public class AssessmentController {

    @Autowired
    IBatchClassService ibcs;
    @Autowired
    IParticipantService ips;
    @Autowired
    ISylabusService iss;
    @Autowired
    IBatchService ibs;
    @Autowired
    IClassesService ics;
    @Autowired
    ICriteriaService ics2;
    @Autowired
    IEmployeeService ies;
    @Autowired
    IEmployeeRoleService iers;
    @Autowired
    ILessonService ils;
    @Autowired
    ILessonCriteriaService ilcs;
    @Autowired
    IAssessmentService ias;
    @Autowired
    IAssessmentDetailService iads;
    @Autowired
    EmployeeRepository er;

    @RequestMapping("/adminpage/adminhome")
    public String home() {
        return "/adminpage/adminhome";
    }

    @RequestMapping("/trainerpage/trainerhome")
    public String home2() {
        return "/trainerpage/trainerhome";
    }

    @GetMapping("/trainerpage/inputnilai")
    public String inputNilai(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        String trainerId = er.getByEmail(user.getUsername()).getId();
        List<BatchClass> batchClasses = new ArrayList<>();
        for (BatchClass bc : ibcs.getAll()) {
            if (bc.getTrainer().getId().equals(trainerId)) {
                batchClasses.add(bc);
            }
        }
        model.addAttribute("batchClasses", batchClasses);
        return "/trainerpage/inputnilai";
    }

    @GetMapping("/trainerpage/participantbybatchclass")
    public String participantByBatchClass(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();
        String trainerId = er.getByEmail(user.getUsername()).getId();
        List<BatchClass> batchClasses = new ArrayList<>();
        for (BatchClass bc : ibcs.getAll()) {
            if (bc.getTrainer().getId().equals(trainerId)) {
                batchClasses.add(bc);
            }
        }
        model.addAttribute("batchClasses", batchClasses);
        return "/trainerpage/participantbybatchclass";
    }

    @PostMapping("/inputnilai")
    public String inputNilai(@RequestParam("nilai") List<String> nilai, @ModelAttribute("criteria") String criteria, @RequestParam("id") List<String> id) {
        for (int i = 0; i < id.size(); i++) {
            if (!nilai.get(i).equals("")) {
                Assessment assessment = null;
                for (Assessment assessment1 : ias.getAll()) {
                    if (assessment1.getParticipant().getId().equals(id.get(i)) && assessment1.getSylabus().getId().equals(ilcs.getById(criteria).getSylabus().getId())) {
                        assessment = assessment1;
                    }
                }
                iads.save(new AssessmentDetail(Float.parseFloat(nilai.get(i)), ilcs.getById(criteria), assessment));
            }
        }
        return "redirect:/trainerpage/inputnilai";
    }

    @GetMapping("/loadform")
    public String loadForm(Model model, String id) {
        List<Participant> participants = new ArrayList<>();
        for (Participant participant : ips.getAll()) {
            if (participant.getBatchClass().getId().equals(id)) {
                participants.add(participant);
            }
        }
        List<Sylabus> sylabuses = new ArrayList<>();
        for (Sylabus sylabus : iss.getAll()) {
            if (sylabus.getClass1().getId().equals(ibcs.getById(id).getClass1().getId())) {
                sylabuses.add(sylabus);
            }
        }
        model.addAttribute("participants", participants);
        model.addAttribute("sylabuses", sylabuses);
        return "content :: form";
    }

    @GetMapping("/loadcriterias")
    public String loadCriterias(Model model, String id) {
        List<LessonCriteria> criterias = new ArrayList<>();
        for (LessonCriteria criteria : ilcs.getAll()) {
            if (criteria.getSylabus().getId().equals(id)) {
                criterias.add(criteria);
            }
        }
        model.addAttribute("criterias", criterias);
        return "content :: criterias2";
    }

    @GetMapping("/loadparticipants")
    public String loadParticipants(Model model, String id) {
        List<Participant> participants = new ArrayList<>();
        for (Participant participant : ips.getAll()) {
            if (participant.getBatchClass().getId().equals(id)) {
                participants.add(participant);
            }
        }
        model.addAttribute("participants", participants);
        return "content :: participantss";
    }
    
    @GetMapping("/loadscores")
    public String loadScores(Model model, String id, String id2) {
        List<AssessmentDetail> scores = new ArrayList<>();
        for (AssessmentDetail assessmentDetail : iads.getAll()) {
            if (assessmentDetail.getLessonCriteria().getId().equals(id) && assessmentDetail.getAssessment().getParticipant().getBatchClass().getId().equals(id2)) {
                scores.add(assessmentDetail);
            }
        }
        model.addAttribute("scores", scores);
        return "content :: scoress";
    }
    
    @GetMapping("/loadscore2")
    public String loadScore2(Model model, String id) {
        List<Assessment> assessments = new ArrayList<>();
        String bcId = ips.getById(id).getBatchClass().getId();
        for (Assessment assessment : ias.getAll()) {
            if (assessment.getParticipant().getId().equals(id)) {
                assessments.add(assessment);
            }
        }
        model.addAttribute("assessments", assessments);
        model.addAttribute("bcId", bcId);
        return "content :: score2";
    }

//    @GetMapping("/testprint")
//    public String testPrint(Model model, String id){
//        System.out.println(id);
//        model.addAttribute("printId", id);
//        return "report";
//    }
    
    
}
