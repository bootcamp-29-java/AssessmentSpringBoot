/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootcamp.cobaspringapplication.services.impl;

import com.bootcamp.cobaspringapplication.services.ILessonService;
import com.bootcamp.cobaspringapplication.entities.Lesson;
import com.bootcamp.cobaspringapplication.repositories.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @authoimport org.springframework.beans.factory.annotation.Autowired;
org.springframework.beans.factory.annotation.Autowired;
r arman
 */
@Service
public class LessonService implements ILessonService {
    
    @Autowired
    LessonRepository er;
    
    @Override
    public String delete(String id) {
        er.deleteById(id);
        return "Delete data berhasil!";
    }

    @Override
    public Iterable<Lesson> getAll() {
        return er.findAll();
    }

    @Override
    public Lesson getById(String id) {
        return er.findById(id).get();
    }

    @Override
    public boolean save(Lesson lesson) {
        return er.save(lesson).equals(lesson);
    }
    
}
