/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootcamp.cobaspringapplication.controllers;

import com.bootcamp.cobaspringapplication.entities.Employee;
import com.bootcamp.cobaspringapplication.services.IEmployeeService;
import java.util.Date;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author ASUS
 */
@Controller
public class EmployeeController {

    @Autowired
    IEmployeeService service;

    @RequestMapping("/adminpage/employee")
    public String showForm(Employee employee, Model model) {
        model.addAttribute("employees", service.getAll());
        return "/adminpage/EmployeeView";
    }

    @GetMapping("/deleteEmployee")
    public String deleteEmployee(String id, RedirectAttributes redirectAttributes) {
        try {
            service.delete(id);
            redirectAttributes.addFlashAttribute("status", "Data Berhasil Dihapus");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("status", "Data Gagal Dihapus");
        }
        return "redirect:/adminpage/employee";
    }

    @GetMapping("/editEmployee")
    public String updateEmployee(Employee employee, Model model) {
        model.addAttribute("employees", service.getAll());
        return "/adminpage/EmployeeView";
    }

    @PostMapping("/inputEmployee")
    public String inputEmployee(@Valid Employee employee, BindingResult result, RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("status", "Data Gagal Disimpan");
            System.out.println(employee.getIsDelete());
            System.out.println(employee.getBirthDate());
            System.out.println(employee.getBirthPlace());
            System.out.println(employee.getEmail());
            System.out.println(employee.getFirstName());
            System.out.println(employee.getLastName());
            System.out.println(employee.getGender());
            System.out.println(employee.getNationality());
        } else {
            redirectAttributes.addFlashAttribute("status", "Data Berhasil Disimpan");
            employee.setPhoto("");
            employee.setIsDelete(false);
            service.save(employee);
        }
        return "redirect:/adminpage/employee";
    }
}
