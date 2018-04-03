package com.workshop.exemplo.controller;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.workshop.exemplo.model.Customer;
import com.workshop.exemplo.repository.CustomerRepository;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerRepository customerRepository;
	
	@PostMapping
	public ResponseEntity<?> create(@Valid @RequestBody Customer customer, HttpServletResponse response) {
		customerRepository.save(customer);
		return ResponseEntity.accepted().build();
	}
	
	@GetMapping
	public ResponseEntity<Iterable<Customer>> list() {
		return ResponseEntity.status(HttpStatus.OK).body(customerRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Customer> read(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(customerRepository.findOne(id));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete (@PathVariable Long id) {
		customerRepository.delete(id);
		return ResponseEntity.accepted().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> update (@Valid @RequestBody Customer customer, HttpServletResponse response) {
		customerRepository.save(customer);
		return ResponseEntity.accepted().build();
	}	
	
}
