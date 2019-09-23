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
import com.bootcamp.cobaspringapplication.services.IBatchClassService;
import com.bootcamp.cobaspringapplication.services.IBatchService;
import com.bootcamp.cobaspringapplication.services.ISylabusService;
import com.bootcamp.cobaspringapplication.services.IParticipantService;
import com.bootcamp.cobaspringapplication.services.IClassesService;
import com.bootcamp.cobaspringapplication.services.IEmployeeRoleService;
import com.bootcamp.cobaspringapplication.services.IEmployeeService;
import java.util.ArrayList;
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
    IEmployeeService ies;
    @Autowired
    IEmployeeRoleService iers;

    @GetMapping("/showbatchclass")
    public String showBatchClass(Model model) {
        model.addAttribute("batchClasses", ibcs.getAll());
        return "showbatchclass";
    }

    @GetMapping("/addbatchclass")
    public String addBatchClass(Model model) {
        model.addAttribute("id", ibs.genId());
        model.addAttribute("classes", ics.getAll());
        List<Employee> trainers = new ArrayList<>();
        for (EmployeeRole employeeRole : iers.getAll()) {
            if (employeeRole.getRole().getName().equals("Trainer")) {
                trainers.add(employeeRole.getEmployee());
            }
        }
        model.addAttribute("trainers", trainers);
        return "addbatchclass";
    }

    @PostMapping("/addbatchclass")
    public String addBatchClass2(Model model, @ModelAttribute("id") String id, @RequestParam("class2") List<String> class2, @ModelAttribute("trainer") String trainer) {
        ibs.save(new Batch(id));
        for (String string : class2) {
            ibcs.save(new BatchClass(id + "/" + string, ibs.getById(id), ics.getById(string), ies.getById(trainer)));
        }
        return "redirect:/addbatchclass";
    }
    
    @GetMapping("/addparticipant")
    public String addParticipant(Model model) {
        model.addAttribute("batchClasses", ibcs.getAll());
        List<Employee> participants = new ArrayList<>();
        for (EmployeeRole employeeRole : iers.getAll()) {
            if (employeeRole.getRole().getName().equals("Participant") && employeeRole.getEmployee().getParticipant() == null) {
                participants.add(employeeRole.getEmployee());
            }
        }
        model.addAttribute("participants", participants);
        return "addparticipant";
    }

    @PostMapping("/addparticipant")
    public String addParticipant2(Model model, @ModelAttribute("batchClass") String batchClass, @RequestParam("participants") List<String> participants) {
        for (String participant : participants) {
            ips.save(new Participant(participant, ibcs.getById(batchClass)));
        }
        return "redirect:/addparticipant";
    }

}
