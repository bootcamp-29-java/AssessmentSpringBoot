/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootcamp.cobaspringapplication.services.impl;

import com.bootcamp.cobaspringapplication.services.IBatchClassService;
import com.bootcamp.cobaspringapplication.entities.BatchClass;
import com.bootcamp.cobaspringapplication.repositories.BatchClassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author arman
 */
@Service
public class BatchClassService implements IBatchClassService{
    
    @Autowired
    BatchClassRepository er;
    
    @Override
    public String delete(String id) {
        er.deleteById(id);
        return "Delete data berhasil!";
    }

    @Override
    public Iterable<BatchClass> getAll() {
        return er.findAll();
    }

    @Override
    public BatchClass getById(String id) {
        return er.findById(id).get();
    }

    @Override
    public boolean save(BatchClass batchClass) {
        return er.save(batchClass).equals(batchClass);
    }
    
}
