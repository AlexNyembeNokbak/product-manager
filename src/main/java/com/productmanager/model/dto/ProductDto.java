package com.productmanager.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Jacksonized
@Builder
public class ProductDto {
	
	private Long id;
	
	@NotBlank(message="Name can't be blank")
	@Size(max=50,message="Maximum name length is 50")
	private String name;
	
	@NotNull(message="The price can't be null")
	@Positive(message="Only positive price values are allowed")
	private Double price;
	
	@NotNull(message="Quantity can't be blank")
	@Positive(message="Only positive quantity values are allowed")
	private Long quantity;

}
