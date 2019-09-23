/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootcamp.cobaspringapplication.services;

import com.bootcamp.cobaspringapplication.entities.Assessment;

/**
 *
 * @author arman
 */
public interface IAssessmentService {

    String delete(String id);

    Iterable<Assessment> getAll();

    Assessment getById(String id);

    boolean save(Assessment assessment);
    
}
