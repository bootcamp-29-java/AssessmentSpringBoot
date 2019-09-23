/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootcamp.cobaspringapplication.services;

import com.bootcamp.cobaspringapplication.entities.Participant;

/**
 *
 * @author arman
 */
public interface IParticipantService {

    String delete(String id);

    Iterable<Participant> getAll();

    Participant getById(String id);

    boolean save(Participant participant);
    
}
