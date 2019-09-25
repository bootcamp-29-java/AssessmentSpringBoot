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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author arman
 */
@Controller
public class BatchClassController {

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

    @GetMapping("/adminpage/managebatchclass")
    public String manageBatchClass(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("batchClasses", ibcs.getAll());
        model.addAttribute("id", ibs.genId());
        model.addAttribute("classes", ics.getAll());
        List<Employee> trainers = new ArrayList<>();
        for (EmployeeRole employeeRole : iers.getAll()) {
            if (employeeRole.getRole().getName().equals("Trainer")) {
                trainers.add(employeeRole.getEmployee());
            }
        }
        model.addAttribute("trainers", trainers);
        return "/adminpage/managebatchclass";
    }

    @PostMapping("/addbatchclass")
    public String addBatchClass(Model model, @ModelAttribute("id") String id, @RequestParam("class2") List<String> class2, @ModelAttribute("trainer") String trainer) {
        ibs.save(new Batch(id));
        for (String string : class2) {
            ibcs.save(new BatchClass(ibs.getById(id), ics.getById(string), ies.getById(trainer)));
        }
        return "redirect:/adminpage/managebatchclass";
    }

    @GetMapping("deleteBatchClass")
    public String deleteBatchClass(String id, RedirectAttributes redirectAttributes) {
        try {
            ibcs.delete(id);
            redirectAttributes.addFlashAttribute("status", "Data Berhasil Dihapus");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("status", "Data Gagal Dihapus");
        }
        return "redirect:/adminpage/managebatchclass";
    }
}
