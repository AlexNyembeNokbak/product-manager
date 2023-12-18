package com.productmanager.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.productmanager.customizedException.ProductFactoryException;
import com.productmanager.model.dto.ErrorDto;
import com.productmanager.model.dto.ProductDto;
import com.productmanager.model.dto.ResponseDto;
import com.productmanager.service.ProductService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@AllArgsConstructor
@Slf4j
public class ProductController {
	
	private ProductService productService;
	
	@PostMapping("/product")
	public ResponseEntity<ResponseDto<ProductDto>> createNewProduct(@RequestBody @Valid ProductDto productDto) throws ProductFactoryException{
		log.info("A request of adding a new product has arrived. Product: {}", productDto);
		ProductDto createdProduct=productService.createNewProduct(productDto);
		ResponseDto<ProductDto> resp=new ResponseDto<>();
		resp.setError(getEmptyError());
		resp.setPayload(createdProduct);
		return new ResponseEntity<>(resp,HttpStatus.CREATED);
	}
	
	@GetMapping("/product/{id}")
	public ResponseEntity<ResponseDto<ProductDto>> getProduct(@PathVariable Long id){
		log.info("A request of product with id: {} , has arrived", id);
		Optional<ProductDto> opProductFound=productService.getProduct(id);
		ResponseDto<ProductDto> resp=new ResponseDto<>();
		if (opProductFound.isEmpty()) {
			resp.setError(getError("ERROR_404","PRODUCT NOT FOUND"));
			resp.setPayload(null);
			return new ResponseEntity<>(resp,HttpStatus.NOT_FOUND);
		}
		resp.setError(getEmptyError());
		resp.setPayload(opProductFound.get());
		return new ResponseEntity<>(resp,HttpStatus.OK);
	}
	
	private ErrorDto getEmptyError() {
		return ErrorDto.builder()
				.code(null)
				.description(null)
				.build();
	}
	
	private ErrorDto getError(String code, String desc) {
		return ErrorDto.builder()
				.code(code)
				.description(desc)
				.build();
	}

}
