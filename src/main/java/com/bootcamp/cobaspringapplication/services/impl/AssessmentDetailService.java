/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootcamp.cobaspringapplication.services.impl;

import com.bootcamp.cobaspringapplication.entities.AssessmentDetail;
import com.bootcamp.cobaspringapplication.services.IAssessmentDetailService;
import com.bootcamp.cobaspringapplication.repositories.AssessmentDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author arman
 */
@Service
public class AssessmentDetailService implements IAssessmentDetailService{
    
    @Autowired
    AssessmentDetailRepository er;
    
    @Override
    public String delete(String id) {
        er.deleteById(id);
        return "Delete data berhasil!";
    }

    @Override
    public Iterable<AssessmentDetail> getAll() {
        return er.findAll();
    }

    @Override
    public AssessmentDetail getById(String id) {
        return er.findById(id).get();
    }

    @Override
    public boolean save(AssessmentDetail asessmentDetail) {
        return er.save(asessmentDetail).equals(asessmentDetail);
    }
    
}
