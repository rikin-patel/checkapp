/**
 * 
 */
package org.rick.checkapp.resources;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.rick.checkapp.model.Group;
import org.rick.checkapp.model.Users;
import org.rick.checkapp.services.GroupService;

/**
 * @author Rikin Patel
 *
 */
@Path("/")
public class GroupsResource {
	
	private GroupService groupService = new GroupService();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Group> getGroups(@Context ServletContext context, @PathParam("userId") Long id){
		return groupService.getAllGroupsOwnedBy(context, id);
	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response createGroup(@Context ServletContext context, @PathParam("userId") Long id, Group group) throws URISyntaxException{
		group.setOwnerId(id);
		Group newGroup = groupService.createGroup(context, group);
		return Response.created(new URI("/checkapp/webapi/user/" + id + "/groups/" + newGroup.getGroupId()))
				.header("Access-Control-Allow-Origin", "*")
				.entity(newGroup).build();
		
	}
}
