/**
 * 
 */
package org.rick.checkapp.resources;

import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;

/**
 * @author Rikin Patel
 *
 */
@Path("/auth")
public class AuthResource {

	@Context
    private ServletContext context;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public void login(@Context HttpHeaders hh){
		
	}
		
}
