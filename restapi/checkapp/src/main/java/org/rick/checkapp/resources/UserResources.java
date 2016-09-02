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
	public Response createUser(Users user) throws URISyntaxException{
		Users newUser = userServices.createUser(context, user);
		return Response.created(new URI("/checkapp/webapi/user/" + newUser.getUserId()))
				.header("Access-Control-Allow-Origin", "*")
				.entity(newUser).build();
		
	}
	
/*	@GET
	@Path("/{userId}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Users> getUserByUserId(@PathParam("userId") long id){
		return userServices.getUsersByUserId(context, id);
	} 
*/
	
	@GET
	@Path("/{emailAddress}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Users> getUserByEmailAddress(@PathParam("emailAddress") String emailAddress){
		return userServices.getUsersByEmailAddress(context, emailAddress);
	}
}
