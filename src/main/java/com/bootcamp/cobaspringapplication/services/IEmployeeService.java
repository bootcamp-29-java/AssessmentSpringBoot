/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootcamp.cobaspringapplication.services;

import com.bootcamp.cobaspringapplication.entities.Employee;

/**
 *
 * @author arman
 */
public interface IEmployeeService {

    String delete(String id);

    Iterable<Employee> getAll();

    Employee getById(String id);

    boolean save(Employee employee);

}
