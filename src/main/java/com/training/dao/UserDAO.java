package com.training.dao;

import java.util.List;

import com.training.domain.User;

public interface UserDAO {

	public List<User> listAllUsers();

	public User getUser(int id);

	public User createUser(User user);

	public User updateUser(int id, User user);

	public User deleteUser(int id);

	public boolean isUserExist(User user);

}