/**
 * 
 */
package org.rick.checkappspringboot.ws.web.api;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.rick.checkappspringboot.ws.model.User;
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

	private static long nextId;
	private static Map<Long, User> users;

	private static User save(User user){
		if(users == null){
			users = new HashMap<Long, User>();
			nextId=1L;
		}

		//if Update...
		if(user.getUserId() != null || user.getUserId() != 0){
			User existingUser = users.get(user.getUserId());
			if(existingUser == null){
				return null;
			}
			users.remove(user.getUserId());
			users.put(user.getUserId(), user);
			return user;
		}

		user.setUserId(nextId);
		nextId ++;
		users.put(user.getUserId(), user);
		return user;
	}
	
	private static boolean delete(long userId){
		User deletedUser = users.remove(userId);
		if(deletedUser == null){
			return false;
		} else {
			return true;
		}
	}

	static{
		User user1 = new User();
		user1.setUserName("Rikin Patel");
		user1.setPassword("Testing1");
		user1.setEmailAddress("riks.lovein@gmail.com");
		user1.setCountryCode("+91");
		user1.setPhoneNumber("9900577698");
		save(user1);

		User user2 = new User();
		user2.setUserName("Ranganath Pabbisetty");
		user2.setPassword("Testing2");
		user2.setEmailAddress("ranganathprn@gmail.com");
		user2.setCountryCode("+91");
		user2.setPhoneNumber("3542365747");
		save(user2);
	}

	@RequestMapping(value="/api/users", 
			method=RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<User>> getUsers(){
		Collection<User> userData = users.values();
		return new ResponseEntity<Collection<User>>(userData, HttpStatus.OK);
	}

	@RequestMapping(value="/api/users/{userId}", 
			method=RequestMethod.GET,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> getUser(@PathVariable("userId") long userId){
		User user = users.get(userId);
		if(user == null){
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}
	}

	@RequestMapping(value="/api/users",
			method=RequestMethod.POST,
			consumes=MediaType.APPLICATION_JSON_VALUE,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> createUser(@RequestBody User user){
		User savedUser = save(user);
		return new ResponseEntity<User>(savedUser, HttpStatus.CREATED);
	}

	@RequestMapping(value="/api/users",
			method=RequestMethod.PUT,
			consumes=MediaType.APPLICATION_JSON_VALUE,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> updateUser(@RequestBody User user){
		User savedUser = save(user);
		if(savedUser == null){
			return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			return new ResponseEntity<User>(savedUser, HttpStatus.CREATED);
		}
	}
	
	@RequestMapping(value="/api/users/{id}",
			method=RequestMethod.DELETE,
			produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> deleteUser(@PathVariable("id") long id, @RequestBody User user){
		
		if(delete(id)){
			return new ResponseEntity<User>(user, HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<User>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
