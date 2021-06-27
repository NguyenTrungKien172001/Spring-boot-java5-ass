package com.ass.dto;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor //sinh ra ham khoi tao ko tham so
@AllArgsConstructor
public class OrderDTO {
	
	private String id;
	
	@NotNull
	@NotBlank
	private String phone;
	
	@NotNull
	@NotBlank
	private String address;
}
