package com.productmanager;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.web.WebAppConfiguration;

import com.productmanager.model.dto.ProductDto;
import com.productmanager.model.jpa.Product;

@SpringBootTest(classes=ProductManagerApplication.class)
@ExtendWith(MockitoExtension.class)
@WebAppConfiguration
public class ProductManagerApplicationTests {

	@Test
	void contextLoads() {
	}
	
	public ProductDto getProductDto() {
		return ProductDto.builder()
				.id(1L)
				.name("Leia")
				.price(1289.56)
				.quantity(12L)
				.build();
	}
	
	public Product getProduct() {
		return Product.builder()
				.id(1L)
				.name("Leia")
				.price(1289.56)
				.quantity(12L)
				.build();
	}

}
