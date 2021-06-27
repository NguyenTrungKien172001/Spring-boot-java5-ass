package com.ass.dto;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDTO {
	
	private Integer id;

	@NotNull
	@NotBlank
	private String name;
	
	@NotNull
	private Integer activate;
}
