/**
 * 
 */
package org.rick.checkappspringboot.ws.web.api;

import java.util.ArrayList;
import java.util.Collection;

import org.rick.checkappspringboot.ws.model.Group;
import org.rick.checkappspringboot.ws.model.User;
import org.rick.checkappspringboot.ws.model.UsersGroups;
import org.rick.checkappspringboot.ws.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Siddharth
 * 
 */
@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/api/users", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<User>> getUsers() {
		Collection<User> userData = userService.findAll();
		return new ResponseEntity<Collection<User>>(userData, HttpStatus.OK);
	}

	@RequestMapping(value = "/api/users/{userId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> getUser(@PathVariable("userId") long userId) {
		User user = userService.findOne(userId);
		if (user == null) {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/api/users/{userId}/owned-groups", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Group>> getOwnedGroupsOfUser(@PathVariable("userId") long userId) {
		User user = userService.findOne(userId);
		if (user == null) {
			return new ResponseEntity<Collection<Group>>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Collection<Group>>(user.getOwnedGroups(), HttpStatus.OK);
		}
	}

	@RequestMapping(value = "/api/users/{userId}/groups", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Group>> getGroupsOfUser(@PathVariable("userId") long userId) {
		User user = userService.findOne(userId);
		Collection<Group> userGroups = new ArrayList<Group>();
		if (user == null) {
			return new ResponseEntity<Collection<Group>>(HttpStatus.NOT_FOUND);
		} else {
			for (UsersGroups userGroup : user.getUsersGroups()) {
				userGroups.add(userGroup.getGroup());
			}
			if(userGroups.isEmpty()){
				return new ResponseEntity<Collection<Group>>(HttpStatus.NOT_FOUND);
			} else  {
				return new ResponseEntity<Collection<Group>>(userGroups, HttpStatus.OK);
			}
		}
	}

	@RequestMapping(value = "/api/users", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> createUser(@RequestBody User user) {
		User savedUser = userService.createUser(user);
		return new ResponseEntity<User>(savedUser, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/api/users", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> updateUser(@RequestBody User user) {
		User savedUser = userService.updateUser(user);
		if (savedUser == null) {
			return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			return new ResponseEntity<User>(savedUser, HttpStatus.CREATED);
		}
	}

	@RequestMapping(value = "/api/users/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> deleteUser(@PathVariable("id") long id,
			@RequestBody User user) {

		userService.delete(id);
		return new ResponseEntity<User>(user, HttpStatus.NO_CONTENT);
	}
}
