/**
 * 
 */
package org.rick.checkappspringboot.ws.web.api;

import java.util.ArrayList;
import java.util.Collection;

import org.rick.checkappspringboot.ws.model.Group;
import org.rick.checkappspringboot.ws.model.User;
import org.rick.checkappspringboot.ws.model.UsersGroups;
import org.rick.checkappspringboot.ws.service.GroupService;
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
 * @author pateriki
 *
 */
@RestController
public class GroupController {

	@Autowired
	private GroupService groupService;
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="/api/groups", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Group>> getGroups(){
		Collection<Group> allGroups = groupService.findAll();
		if(allGroups == null || allGroups.isEmpty()){
			return new ResponseEntity<Collection<Group>>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Collection<Group>>(allGroups, HttpStatus.OK);
		}
	}
	
	@RequestMapping(value="/api/groups/{groupid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Group> getGroup(@PathVariable("groupid") long groupId){
		Group group = groupService.findOne(groupId);
		if(group == null ){
			return new ResponseEntity<Group>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Group>(group, HttpStatus.OK);
		}
	}
	
	@RequestMapping(value="/api/groups/{groupid}/users", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<User>> getUsersOfGroup(@PathVariable("groupid") long groupId){
		Collection<User> allUsers = new ArrayList<User>();
		for (UsersGroups userGroups : groupService.findOne(groupId).getUsersGroups()) {
			allUsers.add(userGroups.getUser());
		}
		if(allUsers == null || allUsers.isEmpty()){
			return new ResponseEntity<Collection<User>>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Collection<User>>(allUsers, HttpStatus.OK);
		}
	}
	
	@RequestMapping(value = "/api/groups", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Group> createUser(@RequestBody Group group) {
		Group savedGroup = groupService.createGroup(group);
		return new ResponseEntity<Group>(savedGroup, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/api/groups", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Group> updateUser(@RequestBody Group group) {
		Group savedGroup = groupService.updateGroup(group);
		if (savedGroup == null) {
			return new ResponseEntity<Group>(HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			return new ResponseEntity<Group>(savedGroup, HttpStatus.CREATED);
		}
	}

	@RequestMapping(value = "/api/groups/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Group> deleteUser(@PathVariable("id") long id,
			@RequestBody Group group) {

		groupService.delete(id);
		return new ResponseEntity<Group>(group, HttpStatus.NO_CONTENT);
	}
	
}
