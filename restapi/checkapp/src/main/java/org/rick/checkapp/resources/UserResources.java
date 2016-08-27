/**
 * 
 */
package org.rick.checkapp.resources;

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

import org.rick.checkapp.model.Users;
import org.rick.checkapp.services.UserService;

/**
 * @author Rikin Patel
 *
 */
@Path("/user")
public class UserResources {

	@Context
    private ServletContext context;
	
	private UserService userServices = new UserService();
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Users createUser(Users user){
		return userServices.createUser(context, user);
	}
	
	@GET
	@Path("/{userId}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Users> getUserByUserId(@PathParam("userId") long id){
		return userServices.getUsersByUserId(context, id);
	}
}
