/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bootcamp.cobaspringapplication.services.impl;

import com.bootcamp.cobaspringapplication.entities.Participant;
import com.bootcamp.cobaspringapplication.repositories.ParticipantRepository;
import com.bootcamp.cobaspringapplication.services.IProductService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author ASUS
 */
@Service("productService")
public class ProductService implements IProductService{

    @Autowired
	private ParticipantRepository participantRepository;

	@Override
	public List<Map<String, Object>> report() {
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		for (Participant participant : participantRepository.findAll()) {
			Map<String, Object> item = new HashMap<String, Object>();
			item.put("id", participant.getId());
			result.add(item);
		}
		return result;
	}
}
