package com.workshop.exemplo.repository;

import org.springframework.data.repository.CrudRepository;

import com.workshop.exemplo.model.Order;

public interface OrderRepository extends CrudRepository<Order, Long>{

}
