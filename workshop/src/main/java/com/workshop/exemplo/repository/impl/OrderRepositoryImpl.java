package com.workshop.exemplo.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.workshop.exemplo.model.dto.MontlyOrders;

public class OrderRepositoryImpl implements OrderRepositoryQuery{

	@PersistenceContext
	private EntityManager manager;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<MontlyOrders> montlyOrders() {
		List<MontlyOrders> vendas = manager.createNamedQuery("queries.listaOrdersItens").getResultList();
		return vendas;
	}
}
