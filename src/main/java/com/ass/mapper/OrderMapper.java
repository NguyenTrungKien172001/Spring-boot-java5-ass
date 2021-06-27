package com.ass.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ass.dto.OrderDTO;
import com.ass.entity.Order;

@Service
public class OrderMapper {
	@Autowired
	private ModelMapper mapper;

	public Order convertToEntity(OrderDTO orderDTO) {
		Order entity = mapper.map(orderDTO, Order.class);

		return entity;
	}

	public OrderDTO convertToDTO(Order entity) {
		OrderDTO orderDTO = mapper.map(entity, OrderDTO.class);

		return orderDTO;
	}
}
