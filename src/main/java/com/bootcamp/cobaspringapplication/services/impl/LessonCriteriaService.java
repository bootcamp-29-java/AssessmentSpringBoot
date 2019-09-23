/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootcamp.cobaspringapplication.services.impl;

import com.bootcamp.cobaspringapplication.services.ILessonCriteriaService;
import com.bootcamp.cobaspringapplication.entities.LessonCriteria;
import com.bootcamp.cobaspringapplication.repositories.LessonCriteriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author arman
 */
@Service
public class LessonCriteriaService implements ILessonCriteriaService{
    
    @Autowired
    LessonCriteriaRepository er;
    
    @Override
    public String delete(String id) {
        er.deleteById(id);
        return "Delete data berhasil!";
    }

    @Override
    public Iterable<LessonCriteria> getAll() {
        return er.findAll();
    }

    @Override
    public LessonCriteria getById(String id) {
        return er.findById(id).get();
    }

    @Override
    public boolean save(LessonCriteria lessonCriteria) {
        return er.save(lessonCriteria).equals(lessonCriteria);
    }
    
}
