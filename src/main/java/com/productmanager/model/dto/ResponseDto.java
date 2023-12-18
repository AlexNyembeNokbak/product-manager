package com.productmanager.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.jackson.Jacksonized;

@Data
@NoArgsConstructor
@Jacksonized
public class ResponseDto<E> {
	
	private ErrorDto error;
	private E payload;

}
