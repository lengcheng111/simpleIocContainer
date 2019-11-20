package com.app;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import com.model.Product;
import com.parser.BeanFactory;
import com.service.ProductService;

public class AppTest {
	public static void main(String[] args)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		final BeanFactory beanFactory = new BeanFactory("config.xml");
		final String serviceName = "storeService";
		final ProductService parse = (ProductService) beanFactory.createBean(serviceName);
		final List<Product> products = parse.getProducts();
		for (final Product product : products) {
			System.out.println(product.getName());
		}
	}
}

class SampleClass {
	private String sampleField;

	public String getSampleField() {
		return sampleField;
	}

	public void setSampleField(String sampleField) {
		this.sampleField = sampleField;
	}
}
