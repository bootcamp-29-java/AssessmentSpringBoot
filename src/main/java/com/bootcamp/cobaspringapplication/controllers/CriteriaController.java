/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootcamp.cobaspringapplication.controllers;

import com.bootcamp.cobaspringapplication.entities.Criteria;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author arman
 */
@Controller
public class CriteriaController {

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

    @PostMapping("/addcriteria")
    public String addCriteria(Model model, @ModelAttribute("id") String id, @ModelAttribute("name") String name, RedirectAttributes redirectAttributes) {
        try {
            ics2.save(new Criteria(id, name));
            redirectAttributes.addFlashAttribute("status", "Data Berhasil Disimpan");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("status", "Data Gagal Disimpan");
        }
        return "redirect:/adminpage/managecriteria";
    }

    @RequestMapping("/adminpage/managecriteria")
    public String manageCriteria(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("criterias", ics2.getAll());
        return "/adminpage/managecriteria";
    }
}
