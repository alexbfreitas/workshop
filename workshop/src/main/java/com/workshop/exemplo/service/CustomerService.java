package com.workshop.exemplo.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.workshop.exemplo.model.Customer;
import com.workshop.exemplo.repository.CustomerRepository;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository repository;
	
	@Transactional
	public Customer update(Long id, Customer toUpdate) {
		if (!toUpdate.getId().equals(id)) {
			throw new IllegalArgumentException();
		}
		Customer updated = repository.save(toUpdate);
		
		return updated;
		
	}
	
	@Transactional
	public void delete(Long id) {
		Customer customer = repository.findOne(id);
		
		if(customer == null ) {
			throw new IllegalArgumentException();
		}
		
		repository.delete(id);
		return;
		
	}	
	
	@Transactional
	public List<Customer> findByNameContainingIgnoreCase(String name) {
		return repository.findByNameContainingIgnoreCase(name);
		
	}	

}
