package com.productmanager.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.productmanager.ProductManagerApplicationTests;
import com.productmanager.customizedException.ProductFactoryException;
import com.productmanager.factory.ProductFactory;
import com.productmanager.model.dto.ProductDto;
import com.productmanager.model.jpa.Product;
import com.productmanager.repository.ProductRepository;

public class ProductServiceTest extends ProductManagerApplicationTests {
	
	@Mock
	private ProductRepository productRepository;
	@Mock
	private ProductFactory productFactory;
	@InjectMocks
	private ProductService productService;
	
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}
	
	@Test
	void createNewProductTestNullProductDtoInput() {
		assertThrows(IllegalArgumentException.class,()->{
			productService.createNewProduct(null);
		});
	}
	
	@Test
	void createNewProductTestEmptyOpCreatedProductFactory() {
		when(productFactory.createProduct(any(ProductDto.class)))
				.thenReturn(Optional.ofNullable(null));
		assertThrows(ProductFactoryException.class,()->{
			productService.createNewProduct(getProductDto());
		});
	}
	
	@Test
	void createNewProductTestSavingProduct() throws ProductFactoryException {
		when(productFactory.createProduct(any(ProductDto.class)))
		.thenReturn(Optional.of(getProduct()));
		
		when(productRepository.save(any(Product.class)))
		.thenReturn(getProduct());
		
		when(productFactory.convertToProductDto(any(Product.class)))
		.thenReturn(Optional.of(getProductDto()));
		
		ProductDto result=productService.createNewProduct(getProductDto());
		assertNotNull(result.getId());
		assertEquals("Leia",result.getName());
		assertEquals(1289.56,result.getPrice());
		assertEquals(12L,result.getQuantity());
	}

}
