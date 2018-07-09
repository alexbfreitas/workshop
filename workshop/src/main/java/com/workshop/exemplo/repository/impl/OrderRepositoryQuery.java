package com.workshop.exemplo.repository.impl;

import java.util.List;

import com.workshop.exemplo.model.dto.MontlyOrders;

public interface OrderRepositoryQuery {

	public List<MontlyOrders> montlyOrders();
}
