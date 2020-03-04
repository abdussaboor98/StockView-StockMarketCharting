package com.cts.training.stockview.userservice;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.cts.training.stockview.userservice.model.User;


@SpringBootTest(classes = UserServiceApplication.class, webEnvironment = WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
class UserServiceApplicationTests {

	@LocalServerPort
	private int port;
	HttpHeaders headers = new HttpHeaders();
	TestRestTemplate restTemplate = new TestRestTemplate();
	
	@Test
	void getTest() {
		String url = "http://localhost:"+port+"/users";
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(null,headers);
		ResponseEntity<String> response = restTemplate.exchange(url,HttpMethod.GET,entity,String.class);
		String expected="{\"id\":181,\"username\":\"admin\",\"email\":\"admin@demo.com\",\"password\":\"admin\",\"phoneNo\":1234567890,\"admin\":true,\"confirmed\":true}";
		System.out.println("Response :: "+response);
		assertTrue(response.getBody().contains(expected));
	}
	@Test
	void getByIdTest() {
		String url = "http://localhost:"+port+"/users/181";
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<String> entity = new HttpEntity<String>(null,headers);
		ResponseEntity<String> response = restTemplate.exchange(url,HttpMethod.GET,entity,String.class);
		String expected="{\"id\":181,\"username\":\"admin\",\"email\":\"admin@demo.com\",\"password\":\"admin\",\"phoneNo\":1234567890,\"admin\":true,\"confirmed\":true}";
		System.out.println("Response :: "+response);
		assertTrue(response.getBody().contains(expected));
	}
	
	@Test
	void postTest() {
		String url = "http://localhost:"+port+"/users";
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		User user = new User(111, "test", "test@yopmail.com", "aaa", 1111111, false, false);
		HttpEntity<User> entity = new HttpEntity<User>(user,headers);
		ResponseEntity<User> response = restTemplate.exchange(url,HttpMethod.POST,entity,User.class);
		String expected="username\":\"test\",\"email\":\"test@yopmail.com\",\"password\":\"aaa\",\"phoneNo\":1111111,\"admin\":false,\"confirmed\":false}";
		System.out.println("Response :: "+response);
		assertTrue(response.getBody().getUsername().equals("test"));
		
	}

}
