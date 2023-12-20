package com.productmanager.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
	
	@Test
	void getProductWithNullId() {
		assertDoesNotThrow(()->{
			productService.getProduct(null);
		});	
		Optional<ProductDto> ris=productService.getProduct(null);
		assertTrue(ris.isEmpty());
	}
	
	@Test
	void getProductWhenNotFoundResource() {
		when(productRepository.findById(any(Long.class)))
		.thenReturn(Optional.ofNullable(null));
		assertDoesNotThrow(()->{
			productService.getProduct(5L);
		});	
		Optional<ProductDto> ris=productService.getProduct(5L);
		assertTrue(ris.isEmpty());
	}
	
	@Test
	void getProductWhenResourceHasBeenFound() {
		Product product=Product.builder()
				.id(5L)
				.name("SmartPhone LG")
				.price(1399.25)
				.quantity(52L)
				.build();
		
		ProductDto productDto=ProductDto.builder()
				.id(5L)
				.name("SmartPhone LG")
				.price(1399.25)
				.quantity(52L)
				.build();
		
		when(productRepository.findById(any(Long.class)))
		.thenReturn(Optional.of(product));
		
		when(productFactory.convertToProductDto(any(Product.class)))
		.thenReturn(Optional.of(productDto));
		
		assertDoesNotThrow(()->{
			productService.getProduct(5L);
		});	
		
		Optional<ProductDto> ris=productService.getProduct(5L);
		assertFalse(ris.isEmpty());
		ProductDto convertedProduct=ris.get();	
		assertEquals(5,convertedProduct.getId());
		assertEquals("SmartPhone LG",convertedProduct.getName());
		assertEquals(1399.25,convertedProduct.getPrice());
		assertEquals(52,convertedProduct.getQuantity());
	}


}
