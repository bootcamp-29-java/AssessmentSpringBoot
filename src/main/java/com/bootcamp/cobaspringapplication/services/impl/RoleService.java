/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootcamp.cobaspringapplication.services.impl;

import com.bootcamp.cobaspringapplication.services.IRoleService;
import com.bootcamp.cobaspringapplication.entities.Role;
import com.bootcamp.cobaspringapplication.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @authoimport org.springframework.beans.factory.annotation.Autowired;
org.springframework.beans.factory.annotation.Autowired;
r arman
 */
@Service
public class RoleService implements IRoleService {
    
    @Autowired
    RoleRepository er;
    
    @Override
    public String delete(String id) {
        er.deleteById(id);
        return "Delete data berhasil!";
    }

    @Override
    public Iterable<Role> getAll() {
        return er.findAll();
    }

    @Override
    public Role getById(String id) {
        return er.findById(id).get();
    }

    @Override
    public boolean save(Role role) {
        return er.save(role).equals(role);
    }
    
}
