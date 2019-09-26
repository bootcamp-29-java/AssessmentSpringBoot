/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootcamp.cobaspringapplication.controllers;

import com.bootcamp.cobaspringapplication.entities.LessonCriteria;
import com.bootcamp.cobaspringapplication.services.ICriteriaService;
import com.bootcamp.cobaspringapplication.services.ILessonCriteriaService;
import com.bootcamp.cobaspringapplication.services.ISylabusService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
public class LessonCriteriaController {
    
    @Autowired
    ILessonCriteriaService ilcs;
    @Autowired
    ISylabusService iss;
    @Autowired
    ICriteriaService ics;
    
    @RequestMapping("/adminpage/LessonCriteriaView")
    public String showDetail(LessonCriteria lessonCriteria, Model model) {
        model.addAttribute("lessonCriterias", ilcs.getAll());
        model.addAttribute("sylabuses", iss.getAll());
        model.addAttribute("criterias", ics.getAll());
        return "/adminpage/LessonCriteriaView";
    }
    
    @GetMapping("/deleteLessonCriteria")
    public String deleteLessonCriteria(String id, RedirectAttributes redirectAttributes) {
        try {
            ilcs.delete(id);
            redirectAttributes.addFlashAttribute("status", "Data Berhasil Dihapus");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("status", "Data Gagal Dihapus");
        }
        return "redirect:/adminpage/LessonCriteriaView";
    }

    @GetMapping("/editLessonCriteria")
    public String updateLessonCriteria(LessonCriteria lessonCriteria, Model model) {
        model.addAttribute("lessonCriterias", ilcs.getAll());
        model.addAttribute("sylabuses", iss.getAll());
        model.addAttribute("criterias", ics.getAll());
        return "/adminpage/LessonCriteriaView";
    }
    
    @PostMapping("/inputLessonCriteria")
    public String inputLessonCriteria(@Valid LessonCriteria lessonCriteria, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("status", "Data Gagal Disimpan");
        } else {
            redirectAttributes.addFlashAttribute("status", "Data Berhasil Disimpan");
            ilcs.save(lessonCriteria);
        }
        return "redirect:/adminpage/LessonCriteriaView";
    }
}
