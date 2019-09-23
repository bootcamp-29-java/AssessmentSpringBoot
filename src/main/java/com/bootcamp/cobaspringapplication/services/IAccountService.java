/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootcamp.cobaspringapplication.services;

import com.bootcamp.cobaspringapplication.entities.Account;

/**
 *
 * @author arman
 */
public interface IAccountService {

    String delete(String id);

    Iterable<Account> getAll();

    Account getById(String id);

    boolean save(Account account);
    
}
