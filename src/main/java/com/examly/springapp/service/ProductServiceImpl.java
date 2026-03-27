
package com.examly.springapp.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

import com.examly.springapp.exceptions.DuplicateProductException;
import com.examly.springapp.model.Product;
import com.examly.springapp.repository.ProductRepo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
 
	private final ProductRepo productRepo;

	@Override
	public Product addProduct(Product product) {
		log.info("we are in addProduct Method in productServiceImpl");
        Optional<Product> prod=productRepo.findById(product.getProductId());
        if(prod.isPresent())
        {
            throw new DuplicateProductException("Product Already Present");
        }
		 return productRepo.save(product);
	}


	@Override
	public Optional<Product> getProductById(Long productId) {
		log.info("we are in getProductById Method in productServiceImpl");
		return productRepo.findById(productId);
	 }


	@Override
	public List<Product> getAllProducts() {
		log.info("we are in getAllProducts Method in productServiceImpl");
		return productRepo.findAll();
	}

	@Override
	public Product updateProduct(Long productId, Product product) {
		log.info("we are in updateProduct Method in productServiceImpl");
		Optional<Product> p=productRepo.findById(productId);
		Product oldProduct=null;
		if(p.isPresent()) {
        oldProduct=p.get();
		oldProduct.setProductName(product.getProductName());
		oldProduct.setDescripion(product.getDescripion());
		oldProduct.setPrice(product.getPrice());
		oldProduct.setStockInteger(product.getStockInteger());
		oldProduct.setCategory(product.getCategory());
		oldProduct.setBrand(product.getBrand());
		oldProduct.setCoverImage(product.getCoverImage());
		
		productRepo.save(oldProduct);
		return oldProduct;
		}
		return null;
	}

	@Override
	public boolean deleteProduct(Long productId) {
		log.info("we are in deleteProduct Method in productServiceImpl");
		Optional<Product> product= productRepo.findById(productId);
        if(product.isPresent()){
        	Product pp=product.get();
            productRepo.delete(pp);
            return true;
        }
        return false;
    }

}

