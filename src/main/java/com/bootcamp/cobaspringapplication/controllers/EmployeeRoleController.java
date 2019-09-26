/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootcamp.cobaspringapplication.controllers;

import com.bootcamp.cobaspringapplication.entities.Employee;
import com.bootcamp.cobaspringapplication.entities.EmployeeRole;
import com.bootcamp.cobaspringapplication.entities.Role;
import com.bootcamp.cobaspringapplication.services.IEmployeeRoleService;
import com.bootcamp.cobaspringapplication.services.IEmployeeService;
import com.bootcamp.cobaspringapplication.services.IRoleService;
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
public class EmployeeRoleController {
    
    @Autowired
    IEmployeeRoleService iers;
    @Autowired
    IRoleService irs;
    @Autowired
    IEmployeeService ies;
    
    @RequestMapping("/adminpage/employeeRole")
    public String showForm(EmployeeRole employeeRole, Employee employee, Role role, Model model) {
//    public String showForm(EmployeeRole employeeRole, Model model){
        model.addAttribute("employeeRoles", iers.getAll());
        model.addAttribute("employees", ies.getAll());
        model.addAttribute("roles", irs.getAll());
        return "/adminpage/employeeRoleView";
    }

    @GetMapping("/deleteEmployeeRole")
    public String deleteEmployeeRole(String id, RedirectAttributes redirectAttributes) {
        try {
            iers.delete(id);
            redirectAttributes.addFlashAttribute("status", "Data Berhasil Dihapus");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("status", "Data Gagal Dihapus");
        }
        return "redirect:/adminpage/employeeRole";
    }

    @GetMapping("/editEmployeeRole")
    public String updateEmployeeRole(EmployeeRole employeeRole, Model model) {
//    public String updateEmployeeRole(EmployeeRole employeeRole, Model model){
        model.addAttribute("employeeRoles", iers.getAll());
        model.addAttribute("employees", ies.getAll());
        model.addAttribute("roles", irs.getAll());
        return "/adminpage/employeeRoleView";
    }

    @PostMapping("/inputEmployeeRole")
    public String inputEmployeeRole(@Valid EmployeeRole employeeRole, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("status", "Data Gagal Disimpan");
        } else {
            redirectAttributes.addFlashAttribute("status", "Data Berhasil Disimpan");
            iers.save(employeeRole);
        }
        return "redirect:/adminpage/employeeRole";
    }
}
