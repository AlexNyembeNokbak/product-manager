package com.productmanager.service;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.productmanager.customizedException.ProductFactoryException;
import com.productmanager.factory.ProductFactory;
import com.productmanager.model.dto.ProductDto;
import com.productmanager.model.jpa.Product;
import com.productmanager.repository.ProductRepository;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class ProductService {
	
	private ProductRepository productRepository;
	
	private ProductFactory productFactory;
	
	public ProductDto createNewProduct(ProductDto productDto) throws ProductFactoryException {
		log.info("Creating new product...");
		
		if (productDto==null) {
			throw new IllegalArgumentException("Can't save a null product!");
		}
		
		Optional<Product> opProduct=productFactory.createProduct(productDto);
		if (opProduct.isEmpty()) {
			throw new ProductFactoryException();
		}
		
		Product product=opProduct.get();
		log.info("Created product from factory: {}",product);
		Product savedProduct=productRepository.save(product);
		log.info("Saved product: {}",savedProduct);
		Optional<ProductDto> opProductDto=productFactory.convertToProductDto(savedProduct);
		
		if (opProductDto.isEmpty()) {
			throw new ProductFactoryException();
		}
		
		return opProductDto.get();
	}
	
	public Optional<ProductDto> getProduct(Long id) {
		Optional<Product> opfoundProduct=productRepository.findById(id);
		if (opfoundProduct.isEmpty()) {
			log.info("Product not found");
			return Optional.ofNullable(null);
		}
		log.info("Found product: {}", opfoundProduct.get());
		return productFactory.convertToProductDto(opfoundProduct.get());
	}

}
