package com.cts.stockview.dao;

import java.util.List;

import com.cts.stockview.model.User;

public interface UserDAO {

	public boolean addUser(User user);

	public boolean updateUser(User user);

	public boolean deleteUser(User user);

	public User getUserById(int id);

	public List<User> getAllUsers();
}
