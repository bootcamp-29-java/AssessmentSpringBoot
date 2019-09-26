/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootcamp.cobaspringapplication.controllers;

import com.bootcamp.cobaspringapplication.entities.Sylabus;
import com.bootcamp.cobaspringapplication.services.IClassesService;
import com.bootcamp.cobaspringapplication.services.ILessonService;
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
public class SylabusController {
    
    @Autowired
    ISylabusService iss;
    @Autowired
    ILessonService ils;
    @Autowired
    IClassesService ics;
    
    @RequestMapping("/adminpage/SylabusView")
    public String showDetail(Sylabus sylabus, Model model) {
        model.addAttribute("sylabuses", iss.getAll());
        model.addAttribute("lessons", ils.getAll());
        model.addAttribute("classes", ics.getAll());
        return "/adminpage/SylabusView";
    }
    
    @GetMapping("/deleteSylabus")
    public String deleteSylabus(String id, RedirectAttributes redirectAttributes) {
        try {
            iss.delete(id);
            redirectAttributes.addFlashAttribute("status", "Data Berhasil Dihapus");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("status", "Data Gagal Dihapus");
        }
        return "redirect:/adminpage/SylabusView";
    }

    @GetMapping("/editSylabus")
    public String updateSylabus(Sylabus sylabus, Model model) {
        model.addAttribute("sylabuses", iss.getAll());
        model.addAttribute("lessons", ils.getAll());
        model.addAttribute("classes", ics.getAll());
        return "/adminpage/SylabusView";
    }
    
    @PostMapping("/inputSylabus")
    public String inputSylabus(@Valid Sylabus sylabus, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("status", "Data Gagal Disimpan");
        } else {
            redirectAttributes.addFlashAttribute("status", "Data Berhasil Disimpan");
            iss.save(sylabus);
        }
        return "redirect:/adminpage/SylabusView";
    }
}
