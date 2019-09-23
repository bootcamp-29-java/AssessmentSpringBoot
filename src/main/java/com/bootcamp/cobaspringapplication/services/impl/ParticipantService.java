/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootcamp.cobaspringapplication.services.impl;

import com.bootcamp.cobaspringapplication.services.IParticipantService;
import com.bootcamp.cobaspringapplication.entities.Participant;
import com.bootcamp.cobaspringapplication.repositories.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author arman
 */
@Service
public class ParticipantService implements IParticipantService{
    
    @Autowired
    ParticipantRepository er;
    
    @Override
    public String delete(String id) {
        er.deleteById(id);
        return "Delete data berhasil!";
    }

    @Override
    public Iterable<Participant> getAll() {
        return er.findAll();
    }

    @Override
    public Participant getById(String id) {
        return er.findById(id).get();
    }

    @Override
    public boolean save(Participant participant) {
        return er.save(participant).equals(participant);
    }
    
}
