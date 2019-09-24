/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootcamp.cobaspringapplication.repositories;

import com.bootcamp.cobaspringapplication.entities.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author arman
 */
public interface EmployeeRepository extends CrudRepository<Employee, String> {

    @Query(value = "SELECT * FROM tb_m_employee where email = ?1", nativeQuery = true)
    public Employee getByEmail(String email);
}
