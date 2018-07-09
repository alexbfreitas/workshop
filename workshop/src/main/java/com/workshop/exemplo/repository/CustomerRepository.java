package com.workshop.exemplo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.workshop.exemplo.model.Customer;
import com.workshop.exemplo.repository.impl.CustomerRepositoryQuery;

public interface CustomerRepository extends CrudRepository<Customer, Long>, CustomerRepositoryQuery{

	public Customer findByEmail(String email);
	public List<Customer> findByNameContainingIgnoreCase(String ss);
}
