/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootcamp.cobaspringapplication.controllers;

import com.bootcamp.cobaspringapplication.entities.Batch;
import com.bootcamp.cobaspringapplication.entities.BatchClass;
import com.bootcamp.cobaspringapplication.entities.Employee;
import com.bootcamp.cobaspringapplication.entities.EmployeeRole;
import com.bootcamp.cobaspringapplication.entities.Participant;
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
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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
public class ParticipantController {

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

    @RequestMapping("/manageparticipant")
    public String manageParticipant(Model model) {
        model.addAttribute("batchClasses", ibcs.getAll());
        List<Employee> participants = new ArrayList<>();
        for (EmployeeRole employeeRole : iers.getAll()) {
            if (employeeRole.getRole().getName().equals("Participant") && employeeRole.getEmployee().getParticipant() == null) {
                participants.add(employeeRole.getEmployee());
            }
        }
        model.addAttribute("participants", participants);
        return "manageparticipant";
    }

    @PostMapping("/addparticipant")
    public String addParticipant(Model model, @ModelAttribute("batchClass") String batchClass, @RequestParam("participants") List<String> participants) {
        for (String participant : participants) {
            ips.save(new Participant(participant, ibcs.getById(batchClass)));
        }
        return "redirect:/manageparticipant";
    }
}