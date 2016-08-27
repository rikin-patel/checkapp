/**
 * 
 */
package org.rick.checkapp.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.rick.checkapp.model.Group;
import org.rick.checkapp.services.GroupService;

/**
 * @author Rikin Patel
 *
 */
@Path("/groups")
public class GroupsResource {
	
	private GroupService groupService = new GroupService();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Group> getGroups(){
		
		return groupService.getAllGroups();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public void createGroup(Group group){
		groupService.addGroup(group);
	}
}
