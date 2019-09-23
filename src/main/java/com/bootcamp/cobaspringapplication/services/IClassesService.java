/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootcamp.cobaspringapplication.services;

import com.bootcamp.cobaspringapplication.entities.Classes;

/**
 *
 * @author arman
 */
public interface IClassesService {

    String delete(String id);

    Iterable<Classes> getAll();

    Classes getById(String id);

    boolean save(Classes classes);
    
}
