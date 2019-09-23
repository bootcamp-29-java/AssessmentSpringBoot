/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootcamp.cobaspringapplication.services;

import com.bootcamp.cobaspringapplication.entities.AssessmentDetail;

/**
 *
 * @author arman
 */
public interface IAssessmentDetailService {

    String delete(String id);

    Iterable<AssessmentDetail> getAll();

    AssessmentDetail getById(String id);

    boolean save(AssessmentDetail assessmentDetail);
    
}
