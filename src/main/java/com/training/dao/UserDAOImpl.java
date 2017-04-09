package com.training.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.training.domain.User;

@Repository
public class UserDAOImpl implements UserDAO {

	private static List<User> users = new ArrayList<User>();

	static {
		users.add(new User(1, "Test 1"));
		users.add(new User(2, "Test 2"));
		users.add(new User(2, "Test 3"));
		users.add(new User(4, "Test 4"));
		users.add(new User(5, "Test 5"));
		users.add(new User(6, "Test 6"));
		users.add(new User(7, "Test 7"));
		users.add(new User(8, "Test 8"));
		users.add(new User(9, "Test 9"));
		users.add(new User(10, "Test 10"));
		users.add(new User(11, "Test 11"));
		users.add(new User(12, "Test 12"));

	}

	public List<User> listAllUsers() {
		return users;
	}

	public User getUser(int id) {
		return users.get(id - 1);
	}

	public User createUser(User user) {
		users.add(user);
		return user;
	}

	public User updateUser(int id, User user) {
		users.get(id - 1).setName(user.getName());
		return users.get(id - 1);
	}

	public User deleteUser(int id) {
		return users.remove(id-1);
	}

	public boolean isUserExist(User user) {
		boolean flag = false;
		for (User u : users)
			if (u.getId() == user.getId())
				flag = true;
		return flag;
	}
}
