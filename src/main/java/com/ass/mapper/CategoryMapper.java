package com.ass.mapper;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ass.dto.CategoryDTO;
import com.ass.entity.Category;

@Service
public class CategoryMapper {
	@Autowired
	private ModelMapper mapper;

	public Category convertToEntity(CategoryDTO categoryDTO) {
		Category entity = mapper.map(categoryDTO, Category.class);

		return entity;
	}

	public CategoryDTO convertToDTO(Category entity) {
		CategoryDTO categoryDTO = mapper.map(entity, CategoryDTO.class);

		return categoryDTO;
	}
}
