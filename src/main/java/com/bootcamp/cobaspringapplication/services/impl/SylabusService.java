/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootcamp.cobaspringapplication.services.impl;

import com.bootcamp.cobaspringapplication.entities.Sylabus;
import com.bootcamp.cobaspringapplication.repositories.SylabusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bootcamp.cobaspringapplication.services.ISylabusService;

/**
 *
 * @author arman
 */
@Service
public class SylabusService implements ISylabusService {
    
    @Autowired
    SylabusRepository er;
    
    @Override
    public String delete(String id) {
        er.deleteById(id);
        return "Delete data berhasil!";
    }

    @Override
    public Iterable<Sylabus> getAll() {
        return er.findAll();
    }

    @Override
    public Sylabus getById(String id) {
        return er.findById(id).get();
    }

    @Override
    public boolean save(Sylabus sylabus) {
        return er.save(sylabus).equals(sylabus);
    }
    
}
