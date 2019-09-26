/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootcamp.cobaspringapplication.services.impl;

import com.bootcamp.cobaspringapplication.entities.EmployeeDummy;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

/**
 *
 * @author Lenovo
 */
@Service
@Transactional
public class EmployeeRest{
    private static RestTemplate restTemplate = new RestTemplate();
    
    public EmployeeDummy login(String email, String password){
        String url = "http://localhost:8083/login/{email}/{password}";
        Map<String, String> map = new HashMap<String, String>();
        map.put("email", email);
        map.put("password", password);
        
        EmployeeDummy employeeDummy = restTemplate.getForObject(url, EmployeeDummy.class, map);
        return employeeDummy;
    }
   
}