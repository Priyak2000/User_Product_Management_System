package com.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.UserRepository;
import com.dto.ProductDTO;
import com.dto.UserDTO;
import com.enitity.Product;
import com.enitity.User;

import jakarta.transaction.Transactional;
@Service
@Transactional
public class UserService {
	@Autowired
	private UserRepository userRepository;

	public void addUser(User user) {
		System.out.println("User Name: " + user.getUserName());
		System.out.println("Password: " + user.getPassWord());
		userRepository.save(user);
	
	}
	public void addUser1(UserDTO userDTO) {
	    User user = new User();
	    user.setUserName(userDTO.getUserName());
	    user.setPassWord(userDTO.getPassWord());
	    user.setProducts(userDTO.getProducts().stream()
	        .map(productDTO -> {
	            Product product = new Product();
	            product.setProdName(productDTO.getProdName());
	            product.setProdPrice(productDTO.getProdPrice());
	            product.setUser(user);
	            return product;
	        })
	        .collect(Collectors.toList()));
	    userRepository.save(user);
	}

	public List<User> getAllUser() {
		
		return userRepository.findAll();
	}

	public void deleteUser(User user, Integer id) {
		try {
			if(id!=null && id!=0) {
				if(userRepository.existsById(id)) {
					userRepository.delete(user);
					System.out.println("User deleted successfully.");
				}
				else {
					System.out.println("User with ID " + id + " does not exist.");
				}
			} else {
	            System.out.println("ID is invalid (cannot be zero or null).");
	        }
	    } catch (Exception e) {
	      
	        System.out.println("An error occurred while trying to delete the user: " + e.getMessage());
	        e.printStackTrace();  
	    }
	}

	public void updateUser(Integer id, User updatedUser) {
		 User existingUser = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
	        existingUser.setUserName(updatedUser.getUserName());
	        userRepository.save(existingUser);
	    
	}		
	}

	

