package org.rick.checkappspringboot.ws.service;

import java.util.Collection;
import java.util.Date;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.rick.checkappspringboot.AbstractTest;
import org.rick.checkappspringboot.ws.model.Group;
import org.rick.checkappspringboot.ws.model.User;
import org.springframework.beans.factory.annotation.Autowired;

public class GroupServiceTest extends AbstractTest {
	
	@Autowired
	GroupService groupService;

	@Autowired
	UserService userService;
	@Before
	public void setUp() throws Exception {
		
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testAddGroup(){
		Group group = new Group();
		group.setCreateDate(new Date());
		group.setDescription("Test Group Created");
		group.setGroupName("TestGroup");
		
		User user = new User();
		user.setUserName("riks.lovein");
		user.setEmailAddress("riks.lovein@gmail.com");
		user.setCountryCode("+91");
		user.setPassword("Testing@123");
		user.setPhoneNumber("8424758742");
		user.setRegDate(new Date());
		group.getUsers().add(user);
		group.setOwnerId(1L);
		
		Group savedGroup = groupService.createGroup(group);
		Assert.assertNotNull("Failure - Object found null, expected Not Null", savedGroup);
		Assert.assertNotNull("Failure - Object found null, expected Not Null", savedGroup.getGroupId());
		Assert.assertEquals("Failure - Expected TestGroup, Found:" + savedGroup.getGroupName(), "TestGroup", savedGroup.getGroupName());
		
		Assert.assertEquals("Failure : Expected : 1", 1L, groupService.findAll().size());
		Assert.assertEquals("Failure : Expected : 1", 1L, savedGroup.getUsers().size());
		
		Assert.assertNotNull("Failure - Object found null, expected Not Null", savedGroup.getOwnerId());
		
		Collection<Group> findByOwnerGroups =  groupService.findByOwnerId(1L);
		Assert.assertEquals("Failure: Expected 1, found:"+findByOwnerGroups.size(), 1L, findByOwnerGroups.size());
		System.out.println("FindByOwners Groups:::" + findByOwnerGroups);
		Assert.assertTrue(findByOwnerGroups.contains(savedGroup));
		
	}

}
