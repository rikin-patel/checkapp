/**
 * 
 */
package org.rick.checkapp.services;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.rick.checkapp.model.Group;
import org.rick.checkapp.model.Users;
import org.rick.checkapp.model.UsersGroups;

/**
 * @author Rikin Patel
 *
 */
public class GroupService {
	
	SolrServer server = new HttpSolrServer("http://localhost:8983/solr/checkapp");
	
	private UserService userService = new UserService();
	
	@SuppressWarnings("unchecked")
	public List<Group> getAllGroupsOwnedBy(ServletContext context, Long userId){
		SessionFactory sessionFactory = (SessionFactory) context.getAttribute("SessionFactory");
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		Users user = (Users) session.get(Users.class, userId);
		List<Group> groups = null;
		try{
			groups = session.createCriteria(Group.class)
									.add(Restrictions.eqOrIsNull("ownerId", userId)).list();
			session.flush();
			session.getTransaction().commit();
			System.out.println("Groups:"+groups);
		} catch(Exception exception){
			exception.printStackTrace();
		}finally{
			if(session.isConnected()){
				session.disconnect();
				session.close();
			}
		}
		return groups;
		
		
	}

	public Group createGroup(ServletContext context, Group group) {
		SessionFactory sessionFactory = (SessionFactory) context.getAttribute("SessionFactory");
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		
		UsersGroups usersGroups = new UsersGroups();
		try{
			Users user = (Users) session.get(Users.class, group.getOwnerId());
			group.setCreateDate(new Date());
			session.save(group);
			
			usersGroups.setUser(user);
			usersGroups.setGroup(group);
			usersGroups.setOwner(true);
			usersGroups.setAssignDate(new Date());
			
			session.save(usersGroups);
			session.flush();
			session.getTransaction().commit();
			
		} catch(Exception exception){
			if(session.isConnected())
				session.getTransaction().rollback();
			exception.printStackTrace();
		}finally{
			if(session.isConnected()){
				session.disconnect();
				session.close();
			}
		}
		try {
			server.addBean(group);
			server.commit();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return group;
	}

}
