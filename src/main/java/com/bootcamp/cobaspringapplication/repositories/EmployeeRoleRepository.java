/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootcamp.cobaspringapplication.repositories;

import com.bootcamp.cobaspringapplication.entities.EmployeeRole;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author arman
 */
public interface EmployeeRoleRepository extends CrudRepository<EmployeeRole, String>{
    
}
