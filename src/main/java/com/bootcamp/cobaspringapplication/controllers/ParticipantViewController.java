/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootcamp.cobaspringapplication.controllers;

import com.bootcamp.cobaspringapplication.entities.Participant;
import com.bootcamp.cobaspringapplication.services.IParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author ASUS
 */
@Controller
public class ParticipantViewController {
    
    @Autowired
    IParticipantService ips;
    
    @RequestMapping("/adminpage/participantview")
    public String showForm(Participant participant, Model model) {
        model.addAttribute("participants", ips.getAll());
        return "/adminpage/ParticipantView";
    }
}
