package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.enitity.Product;
import com.service.ProductService;

@RestController
@RequestMapping("/api")
public class Productcontroller {
	
	@Autowired
	private ProductService productservice;
	
//	@PostMapping("/addProd")
//	public ResponseEntity<Product> createProduct(@RequestBody Product product){
//		Product prod=productservice.createProduct(product);
//		return new ResponseEntity<Product>(prod, HttpStatus.CREATED);
//		
//	}
	@PostMapping("/product")
	public Product saveProduct(@RequestBody Product product) {
	    return productservice.saveProduct(product);
	}

	@GetMapping("/getAllProd")
	public ResponseEntity<List<Product>> getProduct(){
		List prodList=productservice.getAllProduct();
		return new ResponseEntity<List<Product>>(prodList,HttpStatus.OK);
	
	}
	@GetMapping("/getById/{id}")
	public Product getProductById(@PathVariable("id") Integer id){
		return productservice.getProductById(id);
		
	}
	 @PutMapping("/product/{id}")
	    public Product updateEmployee(@PathVariable("id") Integer id, @RequestBody Product prod) {
	        return productservice.updateProductById(id, prod);
	    }

	 @DeleteMapping("/product/{id}")
	 public String deleteProduct(@PathVariable("id") Integer id) {
	     return productservice.deleteProductById(id);
	 }


}
