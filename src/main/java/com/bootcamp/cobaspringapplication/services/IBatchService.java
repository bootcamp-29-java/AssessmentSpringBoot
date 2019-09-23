/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootcamp.cobaspringapplication.services;

import com.bootcamp.cobaspringapplication.entities.Batch;

/**
 *
 * @author arman
 */
public interface IBatchService {

    String delete(String id);

    Iterable<Batch> getAll();

    Batch getById(String id);

    boolean save(Batch batch);
    
    String genId();
    
}
