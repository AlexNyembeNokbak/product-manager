package com.productmanager.factory;

import java.util.Optional;

import org.springframework.stereotype.Component;

import com.productmanager.model.dto.ProductDto;
import com.productmanager.model.jpa.Product;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ProductManagerFactory {
	
	public Optional<Product> createProduct(ProductDto productDto) {
		log.info("Creating product from productDto: {}", productDto);
		
		if (productDto==null) {
			return Optional.ofNullable(null);
		}
		
		Product createdProduct=Product.builder()
				.name(productDto.getName())
				.price(productDto.getPrice())
				.quantity(productDto.getQuantity())
				.build();
		return Optional.of(createdProduct);
	}
	
	public Optional<Product> convertToProduct(ProductDto productDto) {
		log.info("Converting productDto - {} - to product", productDto);
		
		if (productDto==null) {
			return Optional.ofNullable(null);
		}
		
		Product convertedProduct=Product.builder()
				.id(productDto.getId())
				.name(productDto.getName())
				.price(productDto.getPrice())
				.quantity(productDto.getQuantity())
				.build();
		return Optional.of(convertedProduct);
	}
	
	public Optional<ProductDto> convertToProductDto(Product product) {
		log.info("Converting product to productDto");
		
		if (product==null) {
			return Optional.ofNullable(null);
		}
		
		ProductDto convertedDto=ProductDto.builder()
				.id(product.getId())
				.name(product.getName())
				.price(product.getPrice())
				.quantity(product.getQuantity())
				.build();
		return Optional.of(convertedDto);
	}

}
