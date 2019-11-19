package com.repository;

import java.util.ArrayList;
import java.util.List;

import com.model.Product;

public class ProductRepository {

	public List<Product> getAll() {
		List<Product> products = new ArrayList<Product>();
		products.add(new Product(1, "A"));
		products.add(new Product(2, "B"));
		products.add(new Product(3, "C"));
		products.add(new Product(4, "D"));
		return products;
	}
}
