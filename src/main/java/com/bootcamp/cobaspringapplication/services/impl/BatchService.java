/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootcamp.cobaspringapplication.services.impl;

import com.bootcamp.cobaspringapplication.services.IBatchService;
import com.bootcamp.cobaspringapplication.entities.Batch;
import com.bootcamp.cobaspringapplication.repositories.BatchRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @authoimport org.springframework.beans.factory.annotation.Autowired;
 * org.springframework.beans.factory.annotation.Autowired; r arman
 */
@Service
public class BatchService implements IBatchService {

    @Autowired
    BatchRepository er;

    @Override
    public String delete(String id) {
        er.deleteById(id);
        return "Delete data berhasil!";
    }

    @Override
    public Iterable<Batch> getAll() {
        return er.findAll();
    }

    @Override
    public Batch getById(String id) {
        return er.findById(id).get();
    }

    @Override
    public boolean save(Batch batch) {
        return er.save(batch).equals(batch);
    }

    @Override
    public String genId() {
        List<Batch> ids = new ArrayList<>();
        int max = 0;
        for (Batch batch : getAll()) {
            if (Integer.parseInt(batch.getId()) > max) {
                max = Integer.parseInt(batch.getId());
            }
        }
        return ++max + "";
    }

}
