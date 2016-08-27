/**
 * 
 */
package org.rick.checkapp.resources;

import java.util.List;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.rick.checkapp.model.Group;
import org.rick.checkapp.services.GroupService;

/**
 * @author Rikin Patel
 *
 */
@Path("/groups")
public class GroupsResource {
	
	@Context
    private ServletContext context;

	private GroupService groupService = new GroupService();

	@GET
	@Path("/{userId}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Group> getGroups(@PathParam("userId") long id){
		return groupService.getAllGroupsOwnedBy(context, id);
	}

}
