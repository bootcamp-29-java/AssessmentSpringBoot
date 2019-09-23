/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootcamp.cobaspringapplication.services;

import com.bootcamp.cobaspringapplication.entities.Criteria;

/**
 *
 * @author arman
 */
public interface ICriteriaService {

    String delete(String id);

    Iterable<Criteria> getAll();

    Criteria getById(String id);

    boolean save(Criteria criteria);
    
}
