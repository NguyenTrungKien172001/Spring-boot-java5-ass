package com.ass.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemDTO {
	
	private Integer id;
	
	private String name;
	
	private String image;
	
	private Integer price;
	
	private Integer quantity;

}
