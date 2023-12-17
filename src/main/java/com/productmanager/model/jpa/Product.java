package com.productmanager.model.jpa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Entity
@Table(name="PRODUCT")
@Data
@Jacksonized
@Builder
public class Product {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID",nullable=false)
	private Long id;
	
	@Column(name="NAME",nullable=false,length=50)
	private String name;
	
	@Positive
	@Column(name="PRICE",nullable=false)
	private Double price;
	
	@Positive
	@Column(name="QUANTITY",nullable=false)
	private Long quantity;

}
