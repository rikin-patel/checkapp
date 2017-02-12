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
	public void testFindAllGroups(){
		Collection<Group> groups = groupService.findAll();
		Assert.assertNotNull(groups);
		Assert.assertTrue(groups.size() > 0);
	}
	
	@Test
	public void testFindByOwner(){
		Collection<Group> groups = groupService.findByOwnerId(2L);
		Assert.assertNotNull(groups);
		Assert.assertEquals("Failure : Expected : 2 Found" + groups.size(), 2L, groups.size());
	}
	
	@Test
	public void testFindGroupsOfUser(){
		Collection<User> users = groupService.findOne(3).getUsers();
		Assert.assertNotNull(users);
		Assert.assertEquals("Failure : Expected : 2 Found" + users.size(), 2L, users.size());
		
		Collection<User> singleUser = groupService.findOne(4).getUsers();
		Assert.assertNotNull(singleUser);
		Assert.assertEquals("Failure : Expected : 2 Found" + singleUser.size(), 1L, singleUser.size());
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
		
		Assert.assertEquals("Failure : Expected : 5", 5L, groupService.findAll().size());
		Assert.assertEquals("Failure : Expected : 1", 1L, savedGroup.getUsers().size());
		
		Assert.assertNotNull("Failure - Object found null, expected Not Null", savedGroup.getOwnerId());
		
	}
	
	@Test
	public void testDeleteGroup(){
		Group group = new Group();
		group.setCreateDate(new Date());
		group.setDescription("Test Group Deletion");
		group.setGroupName("TestGroup");
		
		group.getUsers().add(userService.findOne(1L));
		group.setOwnerId(1L);
		
		Group savedGroup = groupService.createGroup(group);
		long savedGroupId = savedGroup.getGroupId();
		groupService.delete(savedGroup.getGroupId());
		
		Assert.assertNull(groupService.findOne(savedGroupId));
	}

	@Test
	public void testUpdateGroup(){
		Group group = groupService.findOne(4L);
		String groupName = group.getGroupName();
		
		group.setGroupName(groupName + " Modified");
		Group newGroup = groupService.updateGroup(group);
		
		Assert.assertEquals("Failed,  Found::" + newGroup.getGroupName(), groupName + " Modified", newGroup.getGroupName());
	}
	
	@Test
	public void testUpdateInvalidGroup(){
		Group group = groupService.findOne(4L);
		Group newSavedGroup = groupService.createGroup(group);
		Assert.assertNull(newSavedGroup);
		
		group.setGroupId(0L);
		Group newUpdatedGroup = groupService.updateGroup(group);
		
		Assert.assertNull(newUpdatedGroup);
	}
	
}
