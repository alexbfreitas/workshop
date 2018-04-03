package com.workshop.exemplo.repository;

import org.springframework.data.repository.CrudRepository;

import com.workshop.exemplo.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long>{

}
