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

/**
 * @author Rikin Patel
 *
 */
public class GroupService {
	
	SolrServer server = new HttpSolrServer("http://localhost:8983/solr/checkapp");
	
	private UserService userService = new UserService();
	
	@SuppressWarnings("unchecked")
	public List<Group> getAllGroupsOwnedBy(ServletContext context, Long userId){
		List<Users> user = userService.getUsersByUserId(context, userId);
		SessionFactory sessionFactory = (SessionFactory) context.getAttribute("SessionFactory");
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		List<Group> groups = null;
		try{
			groups = session.createCriteria(Group.class)
									.add(Restrictions.eqOrIsNull("owner", user.get(0))).list();
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
		List<Users> user = userService.getUsersByUserId(context, group.getOwnerId());
		group.setOwner(user.get(0));
		SessionFactory sessionFactory = (SessionFactory) context.getAttribute("SessionFactory");
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		try{
			group.setCreateDate(new Date());
			session.save(group);
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
