package com.workshop.exemplo.controller;

import java.util.List;

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

import com.workshop.exemplo.model.Order;
import com.workshop.exemplo.model.dto.MontlyOrders;
import com.workshop.exemplo.repository.OrderRepository;
import com.workshop.exemplo.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderRepository OrderRepository;
	
	@Autowired
	private OrderService service;
	
	@PostMapping
	public ResponseEntity<?> create(@Valid @RequestBody Order Order, HttpServletResponse response) {
		Order savedOrder = OrderRepository.save(Order);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedOrder);
	}
	
	@GetMapping
	public ResponseEntity<Iterable<Order>> list() {
		return ResponseEntity.status(HttpStatus.OK).body(OrderRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Order> read(@PathVariable Long id) {
		return ResponseEntity.status(HttpStatus.OK).body(OrderRepository.findOne(id));
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete (@PathVariable("id") Long id) {
		service.delete(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<?> update (@PathVariable("id") Long id, @Valid @RequestBody Order Order, HttpServletResponse response) {
		
		Order savedOrder = service.update(id, Order);
		return ResponseEntity.status(HttpStatus.CREATED).body(savedOrder);
	}	
	
	@GetMapping("/vendas")
	public ResponseEntity<?> listaVendas() {
		List<MontlyOrders> vendas = OrderRepository.montlyOrders();
		return vendas.size() > 0 ? ResponseEntity.ok(vendas) : ResponseEntity.notFound().build();
	}
}
