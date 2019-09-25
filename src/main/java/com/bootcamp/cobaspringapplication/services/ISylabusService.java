/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootcamp.cobaspringapplication.services;

import com.bootcamp.cobaspringapplication.entities.Sylabus;

/**
 *
 * @author arman
 */
public interface ISylabusService {

    String delete(String id);

    Iterable<Sylabus> getAll();

    Sylabus getById(String id);

    boolean save(Sylabus sylabus);
    
    public String genId();
}
