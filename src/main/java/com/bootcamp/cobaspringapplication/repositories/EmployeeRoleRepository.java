/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootcamp.cobaspringapplication.repositories;

import com.bootcamp.cobaspringapplication.entities.EmployeeRole;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author arman
 */
public interface EmployeeRoleRepository extends CrudRepository<EmployeeRole, String> {

    @Query(value = "SELECT r.* FROM TB_TR_Employee_Role r RIGHT JOIN TB_M_Employee e ON e.id = r.employee WHERE e.email = ?1", nativeQuery = true)
    public List<EmployeeRole> getRole(String email);
}
