package com.ass.dto;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
	
	private Integer id;
	
	@NotNull
	@NotBlank //chi dung cho chuoi
	private String name;
	
	@NotNull
	@NotBlank
	private String image;
	
	@NotNull
	@Positive
	private Integer price;
	
	@NotNull
	private String createDate;
	
	@NotNull
	@Positive
	private Integer available;
	
	@NotNull
	private Integer categoryId;
	
	@NotNull
	private Integer activate;
	
}
