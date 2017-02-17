/**
 * 
 */
package org.rick.checkappspringboot.ws.service;

import java.util.Collection;

import org.rick.checkappspringboot.ws.model.Group;

/**
 * @author pateriki
 *
 */
public interface GroupService {

		Collection<Group> findAll();
		
		Collection<Group> findByOwnerId(long ownerId);
		
		Group findOne(long groupId);
		
		Group createGroup(Group group);
		
		Group updateGroup(Group group);
		
		void delete (long id);

}
