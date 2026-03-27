
package com.examly.springapp.service;

import java.util.List;
import java.util.Optional;

import com.examly.springapp.model.Product;

public interface ProductService {
    
	public abstract Product addProduct(Product product);
	public abstract Optional<Product> getProductById(Long productId);
	public abstract List<Product> getAllProducts();
	public abstract Product updateProduct(Long productId, Product product);
	public abstract boolean deleteProduct(Long productId);

}


