/**
 * 
 */
package org.rick.checkappspringboot.ws.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.rick.checkappspringboot.AbstractTest;
import org.rick.checkappspringboot.ws.model.Group;
import org.rick.checkappspringboot.ws.model.User;
import org.rick.checkappspringboot.ws.model.UsersGroups;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author pateriki
 *
 */
@Transactional
public class UserServiceTest extends AbstractTest {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private GroupService groupService;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testFindAll() {
		Collection<User> users = userService.findAll();
		logger.info("Size of Users:::"+ users.size());
		Assert.assertNotNull("Failure - Object found null, expected Not Null", users);
		Assert.assertEquals("Expected : 2,  Actual:" + users.size(), 2L, users.size());
	}
	
	@Test
	public void testFindOne() {
		User user = userService.findOne(1);
		Assert.assertNotNull("Failure - Object found null, expected Not Null", user);
		Assert.assertEquals("Expected : 2,  Actual:" + user.getUserId(), new Long(1), user.getUserId());
	}
	
	@Test
	public void testDeleteUser() {
		userService.delete(1);
		Assert.assertNull("Failure - Object found null, expected Not Null", userService.findOne(1));
	}
	
	@Test
	public void testAddUser(){
		User user = new User();
		user.setUserName("riks.lovein");
		user.setEmailAddress("riks.lovein@gmail.com");
		user.setCountryCode("+91");
		user.setPassword("Testing@123");
		user.setPhoneNumber("8424758742");
		user.setRegDate(new Date());
		
		User createdUser = userService.createUser(user);
		Assert.assertNotNull("Failure - Object found null, expected Not Null", createdUser);
		Assert.assertNotNull("Failure - Object found null, expected Not Null", createdUser.getUserId());
		Assert.assertEquals("Failure - Expected riks.lovein, Found:" + createdUser.getUserName(), "riks.lovein", createdUser.getUserName());
		
		Assert.assertEquals("Failure : Expected : 3", 3L, userService.findAll().size());
		
	}
	
	@Test
	public void testAddExistingUser(){
		User user = new User();
		user.setUserId(1L);
		user.setUserName("riks.lovein");
		user.setEmailAddress("riks.lovein@gmail.com");
		user.setCountryCode("+91");
		user.setPassword("Testing@123");
		user.setPhoneNumber("8424758742");
		user.setRegDate(new Date());
		
		User createdUser = userService.createUser(user);
		Assert.assertNull("Failure - Object found null, expected Not Null", createdUser);
		
		Assert.assertEquals("Failure : Expected : 2", 2L, userService.findAll().size());
		
	}
	
	@Test
	public void testAddInvalidUser(){
		
		User user = new User();
		user.setUserName("riks.lovein");
		user.setEmailAddress("riks.lovein@gmail.com");
		user.setCountryCode("+91");
		user.setPassword("Testing@123");
		user.setPhoneNumber("8424758742");
		user.setRegDate(new Date());
		
		User createdUser = userService.createUser(user);
		Assert.assertNotNull("Failure - Object found null, expected Not Null", createdUser);
		Assert.assertNotNull("Failure - Object found null, expected Not Null", createdUser.getUserId());
		Assert.assertEquals("Failure - Expected riks.lovein, Found:" + createdUser.getUserName(), "riks.lovein", createdUser.getUserName());
		
		Assert.assertEquals("Failure : Expected : 3", 3L, userService.findAll().size());
		
		List<Group> savedGroup = new ArrayList<Group>();
		for (UsersGroups group2 : createdUser.getUsersGroups()) {
			savedGroup.add(group2.getGroup());
		}
		Assert.assertEquals("Failure - Expected 0,  Found ", savedGroup.size(), 0);	
		
		List<Group> ownedGroups = createdUser.getOwnedGroups();
		Assert.assertEquals("Failure - Expected 0,  Found ", ownedGroups.size(), 0);
		
	}
	
	@Test
	public void testUpdateUser(){
		User user = new User();
		user.setUserId(2L);
		user.setUserName("riks.lovein12");

		User updatedUser = userService.updateUser(user);
		Assert.assertNotNull("Failure - Object found null, expected Not Null", updatedUser);
		Assert.assertEquals("Failure - Expected:riks.lovein12, Found:" + updatedUser.getUserName(), "riks.lovein12", updatedUser.getUserName());
		
	}
	
	@Test
	public void testUpdateInvalidUser(){
		User user = new User();
		user.setUserId(5L);
		user.setUserName("riks.lovein");

		User updatedUser = userService.updateUser(user);
		Assert.assertNull("Failure - Object found Not null, expected Null", updatedUser);
		
	}

}
