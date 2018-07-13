package com.workshop.exemplo.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.workshop.exemplo.controller.exceptionHandler.OrderNotFoundException;
import com.workshop.exemplo.model.Order;
import com.workshop.exemplo.repository.OrderRepository;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository repository;
	
	@Transactional
	public Order update(Long id, Order toUpdate) {
		if (!toUpdate.getId().equals(id)) {
			throw new IllegalArgumentException();
		}
		Order updated = repository.save(toUpdate);
		
		return updated;
		
	}
	
	@Transactional
	public void delete(Long id) {
		Order order = repository.findById(id).orElseThrow( () -> new OrderNotFoundException("Order not found: ->" + id));
		
		if(order == null ) {
			throw new IllegalArgumentException();
		}
		
		repository.deleteById(id);
		return;
		
	}	
	
	

}
