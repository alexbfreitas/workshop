package com.workshop.exemplo.repository.impl;

import java.util.List;

import com.workshop.exemplo.model.Customer;
import com.workshop.exemplo.model.dto.MontlyOrders;

public interface CustomerRepositoryQuery {

	public List<Customer> listarClientes();
	
	public List<Customer> listarClientesPorNome(String name);
	
}
