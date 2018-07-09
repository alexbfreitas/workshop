package com.workshop.exemplo.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import com.workshop.exemplo.model.Customer;
import com.workshop.exemplo.model.dto.MontlyOrders;

public class CustomerRepositoryImpl implements CustomerRepositoryQuery{

	@PersistenceContext
	private EntityManager manager;
	
	@SuppressWarnings("unchecked")

	@Override
	public List<Customer> listarClientes() {
		
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Customer.class);
		
		return criteria.list();
	}

	@Override
	public List<Customer> listarClientesPorNome(String name) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Customer.class);
		
		criteria.add(Restrictions.ilike("name", name, MatchMode.ANYWHERE));
		
		return criteria.list();
	}
	
}
