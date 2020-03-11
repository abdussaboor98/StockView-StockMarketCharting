package com.cts.training.stockview.userservice.controller;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cts.training.stockview.userservice.model.User;
import com.cts.training.stockview.userservice.service.UserService;

@RestController
public class LoginRestController {
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
			return new ResponseEntity<User>(user,HttpStatus.OK);
		} catch (Exception e ) {
			e.printStackTrace();
			logger.info("Unauthorized access Stack Trace--> {}", e.getStackTrace().toString());
			return new ResponseEntity<String>("No user found",HttpStatus.OK);
		}	
	}
}
