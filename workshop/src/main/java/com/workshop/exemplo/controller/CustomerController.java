package com.workshop.exemplo.controller;

import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.workshop.exemplo.model.Customer;
import com.workshop.exemplo.repository.CustomerRepository;
import com.workshop.exemplo.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private CustomerService service;
	
	@PostMapping
	public ResponseEntity<?> create(@Valid @RequestBody Customer customer, HttpServletResponse response) {
		Customer savedCustomer = customerRepository.save(customer);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedCustomer);
	}
	
	@GetMapping
	public ResponseEntity<Iterable<Customer>> list() {
		return ResponseEntity.status(HttpStatus.OK).body(customerRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> read(@PathVariable Long id) {
		Optional<Customer> customer = customerRepository.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(customer);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete (@PathVariable("id") Long id) {
		service.delete(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update (@PathVariable("id") Long id, @Valid @RequestBody Customer customer, HttpServletResponse response) {
		
		Customer savedCustomer = service.update(id, customer);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedCustomer);
	}	
	
	@GetMapping("/list/{name}")
	public ResponseEntity<?> read(@PathVariable("name") String name) {
		//List<Customer> listaCustomer = customerRepository.findByNameContainingIgnoreCase(name);
		List<Customer> listaCustomer = customerRepository.listarClientesPorNome(name);
		return listaCustomer.size() > 0 ? ResponseEntity.ok(listaCustomer) : ResponseEntity.notFound().build();
	}
	
	@GetMapping("/email/{email:.+}")
	public ResponseEntity<?> getByEmail(@PathVariable("email") String email) {
		Customer customer = customerRepository.findByEmail(email);
		return customer == null? ResponseEntity.notFound().build(): ResponseEntity.ok(customer);
	}
	
	@GetMapping("/teste")
	public ResponseEntity<?> teste() {
		List<Customer> listaCustomer = customerRepository.listarClientes();
		return listaCustomer.size() > 0 ? ResponseEntity.ok(listaCustomer) : ResponseEntity.notFound().build();
	}
	

}
