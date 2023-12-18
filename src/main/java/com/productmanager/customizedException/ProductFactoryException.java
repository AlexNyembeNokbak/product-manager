package com.productmanager.customizedException;

public class ProductFactoryException extends Exception {
	
	public ProductFactoryException() {
		this("Error in factory");
	}
	
	public ProductFactoryException(String errorMsg) {
		super(errorMsg);
	}

}
