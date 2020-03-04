package com.cts.training.stockview.userservice;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.cts.training.stockview.userservice.controller.UserRestController;
import com.cts.training.stockview.userservice.model.User;
import com.cts.training.stockview.userservice.service.UserService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserRestController.class)
class UserServiceRestControllerMockito {

	@Autowired
	MockMvc mockMvc;
	@MockBean
	UserService userService;
	
	@Test
	public void getAllUsers() throws Exception {
		User user = new User(111, "Test", "mockito@mockito.com", "mockito", 11111, false, true);
		List<User> users = new ArrayList<User>();
		users.add(user);
		Mockito.when(userService.getAllUsers()).thenReturn(users);
		
		RequestBuilder request = MockMvcRequestBuilders.get("/users").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(request).andReturn();
		String expected = "{\"id\":111,\"username\":\"Test\",\"email\":\"mockito@mockito.com\",\"password\":\"mockito\",\"phoneNo\":11111,\"admin\":false,\"confirmed\":true}";
		assertTrue(result.getResponse().getContentAsString().contains(expected));
	}
	
	@Test
	public void getByIdUsers() throws Exception {
		User user = new User(111, "Test", "mockito@mockito.com", "mockito", 11111, false, true);
		Mockito.when(userService.getUserById(111)).thenReturn(user);
		
		RequestBuilder request = MockMvcRequestBuilders.get("/users/111").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(request).andReturn();
		String expected = "{\"id\":111,\"username\":\"Test\",\"email\":\"mockito@mockito.com\",\"password\":\"mockito\",\"phoneNo\":11111,\"admin\":false,\"confirmed\":true}";
		assertTrue(result.getResponse().getContentAsString().contains(expected));
	}

}
