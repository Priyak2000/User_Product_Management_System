package com.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.ProductRepository;
import com.enitity.Product;

@Service
public class ProductService {
	@Autowired
	private ProductRepository productRepository;

	public Product saveProduct(Product product) {
	    return productRepository.save(product);
	}


	public List getAllProduct() {
		
		return productRepository.findAll();
	}

	public Product getProductById(Integer id) {
		Optional<Product> prod = productRepository.findById(id);
        if (prod.isPresent()) {
            return prod.get();
        }
		return null;
	}


	public Product updateProductById(Integer id, Product prod) {
	    Optional<Product> prod1 = productRepository.findById(id);

	    if (prod1.isPresent()) {
	        Product originalProd = prod1.get();

	        if (Objects.nonNull(prod.getProdName()) && !"".equalsIgnoreCase(prod.getProdName())) {
	            originalProd.setProdName(prod.getProdName());
	        }

	        if (Objects.nonNull(prod.getProdPrice())) {
	            originalProd.setProdPrice(prod.getProdPrice());
	        }

	        return productRepository.save(originalProd);
	    }
	    
	    return null;
	}
	public String deleteProductById(Integer id) {
	    if (productRepository.findById(id).isPresent()) {
	        productRepository.deleteById(id);
	        return "Product deleted successfully";
	    }
	    return "No such product in the database";
	}

}
