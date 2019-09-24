/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootcamp.cobaspringapplication.controllers;

import com.bootcamp.cobaspringapplication.entities.EmployeeRole;
import com.bootcamp.cobaspringapplication.services.impl.EmployeeRoleService;
import com.bootcamp.cobaspringapplication.services.impl.EmployeeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author asus
 */
@Controller
public class Login {

    @Autowired
    private EmployeeRoleService employeeRoleService;
    
    @Autowired
    private EmployeeService employeeService;

    public List<EmployeeRole> getSession() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        return employeeRoleService.getSession(email);
    }

    @RequestMapping("/")
    public String home(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth.getAuthorities().toString().contains("Admin")){
            return "redirect:/adminpage/adminhome";
        }
        else if(auth.getAuthorities().toString().contains("Trainer")){
            return "redirect:/trainerpage/trainerhome";
        }
        System.out.println(auth.getAuthorities().toString());
        return "login";
    }

    @RequestMapping("/adminpage/home")
    public String adminhome(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return "adminpage/home";
    }

    @RequestMapping("/trainerpage/home")
    public String employeehome(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return "trainerpage/home";
    }

    @RequestMapping("/loginerror")
    public String loginerror(RedirectAttributes redirect) {
        redirect.addFlashAttribute("message", "Invalid Email and Password");
        return "redirect:/login";
    }

    @RequestMapping("/logoutsuccess")
    public String logoutsuccess(RedirectAttributes redirect) {
        redirect.addFlashAttribute("message", "Logout Success");
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String handlingLog() {
        String result = "";
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (!auth.getName().equalsIgnoreCase("anonymousUser")) {
            result = "redirect:/";
        } else {
            result = "login";
        }
        return result;
    }

}
