/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootcamp.cobaspringapplication.services;

import com.bootcamp.cobaspringapplication.entities.LessonCriteria;

/**
 *
 * @author arman
 */
public interface ILessonCriteriaService {

    String delete(String id);

    Iterable<LessonCriteria> getAll();

    LessonCriteria getById(String id);

    boolean save(LessonCriteria lessonCriteria);
    
}
