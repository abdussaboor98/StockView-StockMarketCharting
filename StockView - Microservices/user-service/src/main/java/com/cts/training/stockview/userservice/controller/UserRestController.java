package com.cts.training.stockview.userservice.controller;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.NoSuchElementException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cts.training.stockview.userservice.model.User;
import com.cts.training.stockview.userservice.service.UserService;

@CrossOrigin(origins = "*")
@RestController
public class UserRestController {

	@Autowired
	private UserService userService;
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@GetMapping("/login")
	public ResponseEntity<?> login(HttpServletRequest request) {
		String authorization = request.getHeader("Authorization");
		logger.info("Login attempt with token --> {}", authorization);
		String username = null;
		String password = null;
		if (authorization != null && authorization.startsWith("Basic")) {
		    String base64Credentials = authorization.substring("Basic".length()).trim();
		    byte[] credDecoded = Base64.getDecoder().decode(base64Credentials);
		    String credentials = new String(credDecoded, StandardCharsets.UTF_8);
		    username = credentials.split(":")[0];
		    password = credentials.split(":")[1];
		}
		try {
			User user = userService.getUserByUsernameAndPassword(username, password);
			logger.info("User logged in using username --> {}", username);
			HttpHeaders headers = new HttpHeaders();
			headers.add("Access-Control-Allow-Origin", "*");
			return new ResponseEntity<User>(user,headers,HttpStatus.OK);
		} catch (Exception e ) {
			System.out.println(e.getStackTrace());
			logger.info("Unauthorized access Stack Trace--> {}", e.getStackTrace().toString());
			return new ResponseEntity<String>("No user found",HttpStatus.OK);
		}
		
	}
	
	@GetMapping(value = "/users", produces = "application/json")
	public ResponseEntity<?> getAllUsers() {
		List<User> list = userService.getAllUsers();
		if (list.size() > 0) {
			return new ResponseEntity<List<User>>(list, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("No users found", HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/users/{id}", produces = "application/json")
	public ResponseEntity<?> getUserById(@PathVariable("id") int id) {
		try {
			User user = userService.getUserById(id);
			return new ResponseEntity<User>(user, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<String>("No such user found\n" + e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/users/email/{email}")
	public ResponseEntity<?> getUserByEmail(@PathVariable("email") String email) {
		try {
			User user = userService.getUserByEmail(email);
			return new ResponseEntity<User>(user, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<String>("No such user found", HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping(value = "/users/findByUsernameAndPassword/{username}/{password}")
	public ResponseEntity<?> getUserByUsernameAndPassword(@PathVariable("username") String username,
			@PathVariable("password") String password) {
		try {
			User user = userService.getUserByUsernameAndPassword(username, password);
			return new ResponseEntity<User>(user, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<String>("No such user found", HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping(value = "/users/findByUsername/{username}")
	public ResponseEntity<?> getUserByUsername(@PathVariable("username") String username) {
		try {
			User user = userService.getUserByUsername(username);
			return new ResponseEntity<User>(user, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<String>("No such user found", HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping(value = "/users", consumes = "application/json")
	public ResponseEntity<User> addUser(@RequestBody User user) {
		return new ResponseEntity<User>(userService.addUser(user), HttpStatus.OK);
	}

	@PutMapping(value = "/users/activate")
	public ResponseEntity<?> activateUser(@RequestBody String email) {
		try {
			Boolean status = userService.activateUser(email);
			return new ResponseEntity<Boolean>(status, HttpStatus.OK);
		} catch (NoSuchElementException e) {
			return new ResponseEntity<String>("No such email assigned to user", HttpStatus.BAD_REQUEST);
		}

	}

	@PutMapping(value = "/users", consumes = "application/json")
	public ResponseEntity<?> updateUser(@RequestBody User user) {
		return new ResponseEntity<User>(userService.updateUser(user), HttpStatus.OK);
	}

	@DeleteMapping(value = "/users/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable int id) {
		try {
			userService.deleteUser(id);
			return new ResponseEntity<String>(HttpStatus.NO_CONTENT);
		} catch (IllegalArgumentException e) {
			return new ResponseEntity<String>("No such user found", HttpStatus.BAD_REQUEST);
		}
	}

}
