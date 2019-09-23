/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootcamp.cobaspringapplication.services.impl;

import com.bootcamp.cobaspringapplication.services.IStatusService;
import com.bootcamp.cobaspringapplication.entities.Status;
import com.bootcamp.cobaspringapplication.repositories.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @authoimport org.springframework.beans.factory.annotation.Autowired;
org.springframework.beans.factory.annotation.Autowired;
r arman
 */
@Service
public class StatusService implements IStatusService {
    
    @Autowired
    StatusRepository er;
    
    @Override
    public String delete(String id) {
        er.deleteById(id);
        return "Delete data berhasil!";
    }

    @Override
    public Iterable<Status> getAll() {
        return er.findAll();
    }

    @Override
    public Status getById(String id) {
        return er.findById(id).get();
    }

    @Override
    public boolean save(Status status) {
        return er.save(status).equals(status);
    }
    
}
