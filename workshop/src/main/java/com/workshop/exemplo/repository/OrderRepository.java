package com.workshop.exemplo.repository;

import org.springframework.data.repository.CrudRepository;

import com.workshop.exemplo.model.Order;
import com.workshop.exemplo.repository.impl.OrderRepositoryQuery;

public interface OrderRepository extends CrudRepository<Order, Long>, OrderRepositoryQuery{

}
