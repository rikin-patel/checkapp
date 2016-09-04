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
import org.rick.checkapp.model.Users;

/**
 * @author Rikin Patel
 *
 */
public class UserService {
	
	SolrServer server = new HttpSolrServer("http://localhost:8983/solr/checkapp");

	public Users createUser(ServletContext context, Users user) {
		SessionFactory sessionFactory = (SessionFactory) context.getAttribute("SessionFactory");
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		try{
			user.setRegDate(new Date());
			session.save(user);
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
			server.addBean(user);
			server.commit();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SolrServerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;
	}
	
	@SuppressWarnings("unchecked")
	public List<Users> getUsersByUserId(ServletContext context, Long userId){
		
		SessionFactory sessionFactory = (SessionFactory) context.getAttribute("SessionFactory");
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		List<Users> user = null;
		try{
		user = session.createCriteria(Users.class)
								.add(Restrictions.eqOrIsNull("userId", userId)).list();
		session.flush();
		session.getTransaction().commit();
		System.out.println("User:"+user);
		} catch(Exception exception){
			exception.printStackTrace();
		}finally{
			if(session.isConnected()){
				session.disconnect();
				session.close();
			}
		}
		return user;
	}
	
	@SuppressWarnings("unchecked")
	public List<Users> getUsersByEmailAddress(ServletContext context, String emailAddress){
		
		SessionFactory sessionFactory = (SessionFactory) context.getAttribute("SessionFactory");
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		List<Users> user = null;
		try{
		user = session.createCriteria(Users.class)
								.add(Restrictions.eqOrIsNull("emailAddress", emailAddress)).list();
		session.flush();
		session.getTransaction().commit();
		System.out.println("User:"+user);
		} catch(Exception exception){
			exception.printStackTrace();
		}finally{
			if(session.isConnected()){
				session.disconnect();
				session.close();
			}
		}
		return user;
	}
}
