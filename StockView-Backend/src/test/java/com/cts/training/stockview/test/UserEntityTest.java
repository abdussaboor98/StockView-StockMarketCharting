package com.cts.training.stockview.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.DataIntegrityViolationException;

import com.cts.training.stockview.dao.UserDAO;
import com.cts.training.stockview.daoimpl.UserDAOImpl;
import com.cts.training.stockview.model.UserEntity;

public class UserEntityTest {

	private static AnnotationConfigApplicationContext context;
	private static UserDAO userDAO;
	private static UserEntity user;
	
	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.cts.training.stockview");
		context.refresh();
		user = (UserEntity) context.getBean("userEntity");
		userDAO = (UserDAO) context.getBean("userDAO");
	}
	
	@Test
	public void testGetAllUsers() {
		List<UserEntity> users = userDAO.getAllUsers();
		assertEquals(1, users.size());
	}
	
	@Test
	public void testGetUserByIdSuccess() {
		UserEntity u = new UserEntity(1, "Abdus Saboor", "gaffari98@gmail.com", "abdus123", 9819372849L, false, true);
		assertEquals(u.getEmail(), userDAO.getUserById(1).getEmail());
	}

	@Test
	public void testGetUserByIdFail() {
		assertEquals(null, userDAO.getUserById(50));
	}
	
	@Test
	public void testUpdateUserSuccess() {
		UserEntity u = userDAO.getUserById(1);
		u.setConfirmed(true);
		assertEquals(true, userDAO.updateUser(u));
	}
	
	@Test(expected = NullPointerException.class)
	public void testUpdateUserFail() {
		UserEntity u = userDAO.getUserById(50);
		u.setConfirmed(true);
		assertEquals(true, userDAO.updateUser(u));
	}
	
	@Test
	@Ignore
	public void testAddUserSuccess() {
		UserEntity u = new UserEntity(101, "Abdus Saboor", "gaffari98@gmail.com", "abdus123", 9819372849L, false, true);
		assertEquals(true, userDAO.addUser(u));
	} 
	
	@Test(expected = DataIntegrityViolationException.class)
	public void testAddUserFail() {
		UserEntity u = new UserEntity(101, "Abdus Saboor", "gaffari98@gmail.com", "abdus123", 9819372849L, false, true);
		assertEquals(true, userDAO.addUser(u));
	}
	
	@Test
	@Ignore
	public void testDeleteUserSuccess() {
		UserEntity u = userDAO.getUserById(103);
		assertEquals(true, userDAO.deleteUser(u));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testDeleteUserFail() {
		UserEntity u = userDAO.getUserById(23);
		assertEquals(true, userDAO.deleteUser(u));
	}
	
	@Test
	public void testValidUser() {
		assertEquals(true, userDAO.validateUser("Abdus Saboor", "abdus123"));
	}
	
	@Test
	public void testInValidUser() {
		assertEquals(false, userDAO.validateUser("user", "password"));
	}
	
}
