package com.cts.training.stockview.userservice.service;

import java.util.List;
import java.util.NoSuchElementException;

import com.cts.training.stockview.userservice.model.PasswordChangeRequest;
import com.cts.training.stockview.userservice.model.User;

public interface UserService {

	public List<User> getAllUsers();

	public User getUserById(int id);

	public User getUserByUsernameAndPassword(String username, String password);

	public User addUser(User user);

	public boolean activateUser(String email);

	public User updateUser(User user);

	public void deleteUser(int id);

	User getUserByUsername(String username);

	boolean emailExists(String email) throws NoSuchElementException;

	boolean usernameExists(String email) throws NoSuchElementException;

	public Boolean isUserActive(String username);

	public boolean updatePassword(PasswordChangeRequest request);
}