package com.microservice.authservice.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.authservice.model.User;
import com.microservice.authservice.service.UserService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/authenticate/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("/{userId}")
	public ResponseEntity<?> getUser(@PathVariable("userId") int userId) throws UsernameNotFoundException {

		Optional<User> optional = userService.findById(userId);
		if (optional.isPresent()) {

			return ResponseEntity.ok(optional.get());
		} else {

			throw new UsernameNotFoundException("ID not found");
		}

	}
	
//	@DeleteMapping("/{userId}")
//	public ResponseEntity<?> deleteUser(@PathVariable("userId") String userId) throws IdNotFoundException{
//		if(userService.existsById(userId)) {
//			userService.delete(userId);
//			return ResponseEntity.ok("acc deleted");
//	}else {
//		throw new IdNotFoundException("Id not found");
//	}
//	}

}
