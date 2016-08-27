/**
 * 
 */
package org.rick.checkapp.services;

import java.util.List;

import javax.servlet.ServletContext;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.rick.checkapp.model.Group;

/**
 * @author Rikin Patel
 *
 */
public class GroupService {
	
	@SuppressWarnings("unchecked")
	public List<Group> getAllGroupsOwnedBy(ServletContext context, long userId){
		
		SessionFactory sessionFactory = (SessionFactory) context.getAttribute("SessionFactory");
		Session session = sessionFactory.getCurrentSession();
		List<Group> groups = session.createCriteria(Group.class)
								.add(Restrictions.eqOrIsNull("owner", userId)).list();
		return groups;
		
	}

}
