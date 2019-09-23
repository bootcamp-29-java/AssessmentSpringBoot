/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootcamp.cobaspringapplication.services;

import com.bootcamp.cobaspringapplication.entities.BatchClass;

/**
 *
 * @author arman
 */
public interface IBatchClassService {

    String delete(String id);

    Iterable<BatchClass> getAll();

    BatchClass getById(String id);

    boolean save(BatchClass batchClass);
    
}
