/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootcamp.cobaspringapplication.services.impl;

import com.bootcamp.cobaspringapplication.entities.Employee;
import com.bootcamp.cobaspringapplication.repositories.EmployeeRepository;
import com.bootcamp.cobaspringapplication.services.IEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author arman
 */
@Service
public class EmployeeService implements IEmployeeService{
    
    @Autowired
    EmployeeRepository er;
    
    @Override
    public String delete(String id) {
        er.deleteById(id);
        return "Delete data berhasil!";
    }

    @Override
    public Iterable<Employee> getAll() {
        return er.findAll();
    }

    @Override
    public Employee getById(String id) {
        return er.findById(id).get();
    }

    @Override
    public boolean save(Employee employee) {
        return er.save(employee).equals(employee);
    }
    
}
