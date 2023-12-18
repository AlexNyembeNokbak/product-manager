package com.productmanager.factory;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.productmanager.ProductManagerApplicationTests;
import com.productmanager.model.dto.ProductDto;
import com.productmanager.model.jpa.Product;

public class ProductFactoryTest extends ProductManagerApplicationTests {
	
	@Autowired
	private ProductFactory productFactory;
	
	@Test
	void createProductFromNullProductDtoTestDoesNotThrowException() {
		assertDoesNotThrow(() -> {
			productFactory.createProduct(null);
		});
	}
	
	@Test
	void createProductFromNullProductDtoTestEmptyResult() {
		Optional<Product> op=productFactory.createProduct(null);
		assertFalse(op.isPresent());
	}
	
	@Test
	void createProductFromNotNullProductDtoTestNotEmptyResult() {
		ProductDto productDto=getProductDto();
		
		assertDoesNotThrow(() -> {
			productFactory.createProduct(productDto);
		});
		
		Optional<Product> op=productFactory.createProduct(productDto);
		assertTrue(op.isPresent());
		assertNull(op.get().getId());
		assertEquals("Leia",op.get().getName());
		assertEquals(1289.56,op.get().getPrice());
		assertEquals(12L,op.get().getQuantity());
	}
	
	@Test
	void createProductDtoFromNullProductTestDoesNotThrowException() {
		assertDoesNotThrow(() -> {
			productFactory.convertToProductDto(null);
		});
	}
	
	@Test
	void createProductDtoFromNullProductTestEmptyResult() {
		Optional<ProductDto> op=productFactory.convertToProductDto(null);
		assertFalse(op.isPresent());
	}
	
	@Test
	void convertToProductDtoTestNotEmptyResult() {
		Product product=getProduct();
		
		assertDoesNotThrow(() -> {
			productFactory.convertToProductDto(product);
		});
		
		Optional<ProductDto> op=productFactory.convertToProductDto(product);
		assertTrue(op.isPresent());
		assertNotNull(op.get().getId());
		assertEquals("Leia",op.get().getName());
		assertEquals(1289.56,op.get().getPrice());
		assertEquals(12L,op.get().getQuantity());
	}
	
	

}
