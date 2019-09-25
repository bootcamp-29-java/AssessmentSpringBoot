/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootcamp.cobaspringapplication.controllers;

import com.bootcamp.cobaspringapplication.entities.Classes;
import com.bootcamp.cobaspringapplication.entities.Sylabus;
import com.bootcamp.cobaspringapplication.services.IAssessmentDetailService;
import com.bootcamp.cobaspringapplication.services.IAssessmentService;
import com.bootcamp.cobaspringapplication.services.IBatchClassService;
import com.bootcamp.cobaspringapplication.services.IBatchService;
import com.bootcamp.cobaspringapplication.services.IClassesService;
import com.bootcamp.cobaspringapplication.services.ICriteriaService;
import com.bootcamp.cobaspringapplication.services.IEmployeeRoleService;
import com.bootcamp.cobaspringapplication.services.IEmployeeService;
import com.bootcamp.cobaspringapplication.services.ILessonCriteriaService;
import com.bootcamp.cobaspringapplication.services.ILessonService;
import com.bootcamp.cobaspringapplication.services.IParticipantService;
import com.bootcamp.cobaspringapplication.services.ISylabusService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author arman
 */
@Controller
public class InputSylabusController {

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

    @GetMapping("/testsylabus")
    public String manageBatchClass(Model model) {
        model.addAttribute("lessons", ils.getAll());
        return "testsylabus";
    }

    @PostMapping("/addlessons")
    public String addLessons(Model model, @ModelAttribute("class") String className, @ModelAttribute("classId") String classId, @RequestParam("lessons") List<String> lessons, @RequestParam("percentages") List<String> percentages, @RequestParam("ids") List<String> ids) {
        ics.save(new Classes(classId, className));
        for (int i = 0; i < lessons.size(); i++) {
            iss.save(new Sylabus(Float.parseFloat(percentages.get(i)), ils.getById(lessons.get(i)), ics.getById(classId)));
        }
        return "redirect:/testsylabus";
    }
}
