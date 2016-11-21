/**
 * 
 */
package org.rick.checkappspringboot.ws.service;

import java.util.Collection;

import org.rick.checkappspringboot.ws.model.User;

/**
 * @author pateriki
 *
 */
public interface UserService {

	Collection<User> findAll();
	
	User findOne(long userId);
	
	User createUser(User user);
	
	User updateUser(User user);
	
	void delete (long id);
}
