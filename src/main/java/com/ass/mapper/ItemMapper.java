package com.ass.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ass.dto.ItemDTO;
import com.ass.entity.Product;

@Service
public class ItemMapper {
	@Autowired
	private ModelMapper mapper;

	public Product convertToEntity(ItemDTO itemDTO) {
		Product entity = mapper.map(itemDTO, Product.class);

		return entity;
	}

	public ItemDTO convertToDTO(Product entity) {
		ItemDTO itemDTO = mapper.map(entity, ItemDTO.class);

		return itemDTO;
	}
}
