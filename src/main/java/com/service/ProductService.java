package com.service;

import java.util.List;

import com.model.Product;
import com.repository.ProductRepository;

public class ProductService {
	private ProductRepository productRepository;

	public ProductRepository getProductRepository() {
		return productRepository;
	}

	public void setProductRepository(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	public List<Product> getProducts() {
		return this.productRepository.getAll();
	}

}
