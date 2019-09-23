/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootcamp.cobaspringapplication.services.impl;

import com.bootcamp.cobaspringapplication.services.ICriteriaService;
import com.bootcamp.cobaspringapplication.entities.Criteria;
import com.bootcamp.cobaspringapplication.repositories.CriteriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author arman
 */
@Service
public class CriteriaService implements ICriteriaService{
    
    @Autowired
    CriteriaRepository er;
    
    @Override
    public String delete(String id) {
        er.deleteById(id);
        return "Delete data berhasil!";
    }

    @Override
    public Iterable<Criteria> getAll() {
        return er.findAll();
    }

    @Override
    public Criteria getById(String id) {
        return er.findById(id).get();
    }

    @Override
    public boolean save(Criteria criteria) {
        return er.save(criteria).equals(criteria);
    }
    
}
