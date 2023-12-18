package com.productmanager.model.jpa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.jackson.Jacksonized;

@Entity
@Table(name="PRODUCT")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Jacksonized
@Builder
public class Product {
	
	@Id
	@SequenceGenerator(name= "PRODUCT_SEQUENCE", sequenceName = "PRODUCT_SEQUENCE_ID", initialValue=1, allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.AUTO, generator="PRODUCT_SEQUENCE")
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
