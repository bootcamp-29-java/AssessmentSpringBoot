/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootcamp.cobaspringapplication.services.impl;

import com.bootcamp.cobaspringapplication.entities.Classes;
import com.bootcamp.cobaspringapplication.repositories.ClassesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bootcamp.cobaspringapplication.services.IClassesService;

/**
 *
 * @author arman
 */
@Service
public class ClassesService implements IClassesService{
    
    @Autowired
    ClassesRepository er;
    
    @Override
    public String delete(String id) {
        er.deleteById(id);
        return "Delete data berhasil!";
    }

    @Override
    public Iterable<Classes> getAll() {
        return er.findAll();
    }

    @Override
    public Classes getById(String id) {
        return er.findById(id).get();
    }

    @Override
    public boolean save(Classes classes) {
        return er.save(classes).equals(classes);
    }
    
}
