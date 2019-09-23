/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootcamp.cobaspringapplication.services;

import com.bootcamp.cobaspringapplication.entities.EmployeeRole;

/**
 *
 * @author arman
 */
public interface IEmployeeRoleService {

    String delete(String id);

    Iterable<EmployeeRole> getAll();

    EmployeeRole getById(String id);

    boolean save(EmployeeRole employeeRole);
    
}
