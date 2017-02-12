/**
 * 
 */
package org.rick.checkappspringboot.ws.web.api;

import java.util.Collection;

import org.rick.checkappspringboot.ws.model.Group;
import org.rick.checkappspringboot.ws.model.User;
import org.rick.checkappspringboot.ws.service.GroupService;
import org.rick.checkappspringboot.ws.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
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
		return new ResponseEntity<Collection<Group>>(groupService.findAll(), HttpStatus.OK);
	}
	
	@RequestMapping(value="/api/groups/{groupid}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Group> getGroup(@PathVariable("groupid") long groupId){
		return new ResponseEntity<Group>(groupService.findOne(groupId), HttpStatus.OK);
	}
	
	@RequestMapping(value="/api/groups/{groupid}/users", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<User>> getUsersOfGroup(@PathVariable("groupid") long groupId){
		return new ResponseEntity<Collection<User>>(groupService.findOne(groupId).getUsers(), HttpStatus.OK);
	}
	
	
}
