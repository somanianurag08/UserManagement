package com.training.restcontroller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.training.domain.User;
import com.training.service.UserService;
import com.training.util.CustomErrorType;

@RestController
@RequestMapping("/user")
public class UserRestController {

	public static final Logger logger = LoggerFactory
			.getLogger(UserRestController.class);

	@Autowired
	UserService userService; // Service which will do all data
								// retrieval/manipulation work

	// -------------------Retrieve All
	// Users---------------------------------------------

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/listAllUsers/", method = RequestMethod.GET)
	public ResponseEntity<List<User>> listAllUsers() {
		List<User> users = userService.listAllUsers();
		if (users.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}

	// -------------------Retrieve Single
	// User------------------------------------------

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@RequestMapping(value = "/getUser/{id}", method = RequestMethod.GET)
	public ResponseEntity<User> getUser(@PathVariable("id") int id) {
		logger.info("Fetching User with id {}", id);
		User user = null;
		try {
			user = userService.getUser(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (user == null) {
			logger.error("User with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType("User with id " + id
					+ " not found"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	// -------------------Create a
	// User-------------------------------------------

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/createUser/", method = RequestMethod.POST)
	public ResponseEntity<User> createUser(@RequestBody User user) {
		logger.info("Creating User : {}", user);

		if (userService.isUserExist(user)) {
			logger.error("Unable to create. A User with name {} already exist",
					user.getName());
			return new ResponseEntity(new CustomErrorType(
					"Unable to create. A User with name " + user.getName()
							+ " already exist."), HttpStatus.CONFLICT);
		}
		userService.createUser(user);

		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	// ------------------- Update a User
	// ------------------------------------------------

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/updateUser/{id}", method = RequestMethod.PUT)
	public ResponseEntity<User> updateUser(@PathVariable("id") int id,
			@RequestBody User user) {
		logger.info("Updating User with id {}", id);

		User currentUser = userService.getUser(id);

		if (currentUser == null) {
			logger.error("Unable to update. User with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType(
					"Unable to upate. User with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}

		currentUser.setName(user.getName());

		userService.updateUser(currentUser.getId(), currentUser);
		return new ResponseEntity<User>(currentUser, HttpStatus.OK);
	}

	// ------------------- Delete a
	// User-----------------------------------------

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping(value = "/deleteUser/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<User> deleteUser(@PathVariable("id") int id) {
		logger.info("Fetching & Deleting User with id {}", id);

		User user = userService.getUser(id);
		if (user == null) {
			logger.error("Unable to delete. User with id {} not found.", id);
			return new ResponseEntity(new CustomErrorType(
					"Unable to delete. User with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}
		userService.deleteUser(id);
		return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
	}
}