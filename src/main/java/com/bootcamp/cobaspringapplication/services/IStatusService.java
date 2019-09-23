/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootcamp.cobaspringapplication.services;

import com.bootcamp.cobaspringapplication.entities.Status;

/**
 *
 * @author arman
 */
public interface IStatusService {

    String delete(String id);

    Iterable<Status> getAll();

    Status getById(String id);

    boolean save(Status status);
    
}
