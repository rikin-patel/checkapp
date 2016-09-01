package org.rick.checkapp.filters;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.ServletContext;

import org.rick.checkapp.model.Users;
import org.rick.checkapp.services.UserService;

public class AuthenticationService {
	public boolean authenticate(ServletContext context, String authCredentials) {

		if (null == authCredentials)
			return false;
		// header value format will be "Basic encodedstring" for Basic
		// authentication. Example "Basic YWRtaW46YWRtaW4="
		final String encodedUserPassword = authCredentials.replaceFirst("Basic"
				+ " ", "");
		String usernameAndPassword = null;
		try {
			byte[] decodedBytes = Base64.getDecoder().decode(
					encodedUserPassword);
			usernameAndPassword = new String(decodedBytes, "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		final StringTokenizer tokenizer = new StringTokenizer(
				usernameAndPassword, ":");
		final String username = tokenizer.nextToken();
		final String password = tokenizer.nextToken();

		UserService userService = new UserService();
		List<Users> userList = userService.getUsersByEmailAddress(context, username);
		// we have fixed the userid and password as admin
		// call some UserService/LDAP here
		boolean authenticationStatus = false;
		for (Users user : userList ) {
			 authenticationStatus = (user!=null) ? 
					 user.getEmailAddress().equals(username) && user.getPassword().equals(password) : 
						 false; 
			if(authenticationStatus){
				return authenticationStatus;
			}
		}
		System.out.println("Users List:::" + userList);
		return authenticationStatus;
		
	}
}