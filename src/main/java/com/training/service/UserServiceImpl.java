package com.training.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.training.dao.UserDAO;
import com.training.domain.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;

	public List<User> listAllUsers() {
		return userDAO.listAllUsers();
	}

	public User getUser(int id) {
		return userDAO.getUser(id);
	}

	public User createUser(User user) {
		return userDAO.createUser(user);
	}

	public User updateUser(int id, User user) {
		return userDAO.updateUser(id, user);
	}

	public User deleteUser(int id) {
		return userDAO.deleteUser(id);
	}

	public boolean isUserExist(User user) {
		return userDAO.isUserExist(user);
	}
}