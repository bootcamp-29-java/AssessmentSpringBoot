/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootcamp.cobaspringapplication.services;

import com.bootcamp.cobaspringapplication.entities.Role;

/**
 *
 * @author arman
 */
public interface IRoleService {

    String delete(String id);

    Iterable<Role> getAll();

    Role getById(String id);

    boolean save(Role role);
    
}
