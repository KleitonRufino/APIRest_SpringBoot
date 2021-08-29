package com.example.demo.service;

import java.util.Collection;

import com.example.demo.entity.Product;

public interface ProductService {

	public abstract void createProduct(Product product);
	public abstract void updateProduct(String id, Product product);
	public abstract void deleteProduct(String id);
	public abstract Collection<Product> getProducts();
	public abstract Product getProduct(String id);
}
