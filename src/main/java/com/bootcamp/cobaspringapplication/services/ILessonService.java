/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootcamp.cobaspringapplication.services;

import com.bootcamp.cobaspringapplication.entities.Lesson;

/**
 *
 * @author arman
 */
public interface ILessonService {

    String delete(String id);

    Iterable<Lesson> getAll();

    Lesson getById(String id);

    boolean save(Lesson lesson);
    
}
