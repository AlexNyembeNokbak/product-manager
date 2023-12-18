package com.productmanager.model.dto;

import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Jacksonized
@Builder
public class ErrorDto {
	
	private String code;
	private String description;

}
