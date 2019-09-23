/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootcamp.cobaspringapplication.services.impl;

import com.bootcamp.cobaspringapplication.services.IAssessmentService;
import com.bootcamp.cobaspringapplication.entities.Assessment;
import com.bootcamp.cobaspringapplication.repositories.AssessmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author arman
 */
@Service
public class AssessmentService implements IAssessmentService{
    
    @Autowired
    AssessmentRepository er;
    
    @Override
    public String delete(String id) {
        er.deleteById(id);
        return "Delete data berhasil!";
    }

    @Override
    public Iterable<Assessment> getAll() {
        return er.findAll();
    }

    @Override
    public Assessment getById(String id) {
        return er.findById(id).get();
    }

    @Override
    public boolean save(Assessment assessment) {
        return er.save(assessment).equals(assessment);
    }
    
}
