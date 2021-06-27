package com.ass.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ass.dto.ProductDTO;
import com.ass.entity.Product;

@Service
public class ProductMapper {
	@Autowired
	private ModelMapper mapper;

	public Product convertToEntity(ProductDTO productDTO) {
		Product entity = mapper.map(productDTO, Product.class);

		return entity;
	}

	public ProductDTO convertToDTO(Product entity) {
		ProductDTO productDTO = mapper.map(entity, ProductDTO.class);

		return productDTO;
	}
}
