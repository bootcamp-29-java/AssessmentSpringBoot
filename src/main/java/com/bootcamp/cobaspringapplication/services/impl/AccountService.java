/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootcamp.cobaspringapplication.services.impl;

import com.bootcamp.cobaspringapplication.services.IAccountService;
import com.bootcamp.cobaspringapplication.entities.Account;
import com.bootcamp.cobaspringapplication.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author arman
 */
@Service
public class AccountService implements IAccountService{
    
    @Autowired
    AccountRepository er;
    
    @Override
    public String delete(String id) {
        er.deleteById(id);
        return "Delete data berhasil!";
    }

    @Override
    public Iterable<Account> getAll() {
        return er.findAll();
    }

    @Override
    public Account getById(String id) {
        return er.findById(id).get();
    }

    @Override
    public boolean save(Account account) {
        return er.save(account).equals(account);
    }
    
}
