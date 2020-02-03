package com.cts.stockview.controller;

import com.cts.stockview.dao.UserDAO;
import com.cts.stockview.daoimpl.UserDAOImpl;
import com.cts.stockview.model.User;

public class UserController {

	public static void main(String[] args) {
//		User user =new User(101, "abdussaboor", "gaffari98@gmail.com", "abdus123", 979879887L, true, true);
		UserDAO userDAO = new UserDAOImpl();
//		
//		userDAO.addUser(user);
		
		System.out.println(userDAO.validateUser("abdussaboor", "abdus123"));
	}
}
