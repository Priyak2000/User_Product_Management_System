package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dto.UserDTO;
import com.enitity.User;
import com.service.UserService;

@RestController
@RequestMapping("/api")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
//	@PostMapping("/addUser")	
//	public String addUser(@RequestBody User user){
//		System.out.println("Received User: " + user);
//			userService.addUser(user);
//		return "User details added successfully!";
//	}
	@PostMapping("/addUser")
    public ResponseEntity<String> addUser(@RequestBody User user) {
        System.out.println("Received User: " + user.getUserName() + ", " + user.getPassWord());

        if (user.getUserName() == null || user.getPassWord() == null) {
            return ResponseEntity.badRequest().body("User name and password are required");
        }

        // Assuming you have a userService.addUser(user) method
        userService.addUser(user);
        
        return ResponseEntity.ok("User added successfully");
    }
	@PostMapping("/addUser1")
    public ResponseEntity<String> addUser(@RequestBody UserDTO userDTO) {
        // Call service to add user and products
		userDTO.setPassWord(passwordEncoder.encode(userDTO.getPassWord()));
        userService.addUser1(userDTO);
        return ResponseEntity.ok("User and products added successfully!");
    }
	
	@GetMapping("/allUser")
	public ResponseEntity<List<User>> getUser(){
		List<User> u=userService.getAllUser();
		return new ResponseEntity<>(u,HttpStatus.OK);
	}
	@DeleteMapping("/deleteByid/{id}")
	public String deleteById(@PathVariable("id") Integer id , User user) {
		userService.deleteUser(user,id);
		return "Data deleted";	
	}
	@PutMapping("/{id}")
	public ResponseEntity<String> updateUser(@PathVariable Integer id, @RequestBody User updatedUser) {
	    userService.updateUser(id, updatedUser);
	    return ResponseEntity.ok("User updated");
	}
	@GetMapping("/dashboard")
	public String Dashboard() {
		System.out.println("Reached the Dashboard Controller Layer");
		return "Welcome to dashboard!";
	}

}
