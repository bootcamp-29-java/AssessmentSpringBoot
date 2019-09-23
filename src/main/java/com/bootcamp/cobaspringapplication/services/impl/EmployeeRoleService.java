/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootcamp.cobaspringapplication.services.impl;

import com.bootcamp.cobaspringapplication.services.IEmployeeRoleService;
import com.bootcamp.cobaspringapplication.entities.EmployeeRole;
import com.bootcamp.cobaspringapplication.repositories.EmployeeRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author arman
 */
@Service
public class EmployeeRoleService implements IEmployeeRoleService {
    
    @Autowired
    EmployeeRoleRepository er;
    
    @Override
    public String delete(String id) {
        er.deleteById(id);
        return "Delete data berhasil!";
    }

    @Override
    public Iterable<EmployeeRole> getAll() {
        return er.findAll();
    }

    @Override
    public EmployeeRole getById(String id) {
        return er.findById(id).get();
    }

    @Override
    public boolean save(EmployeeRole employeeRole) {
        return er.save(employeeRole).equals(employeeRole);
    }
    
}
