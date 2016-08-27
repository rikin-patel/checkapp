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
import org.rick.checkapp.model.Users;

/**
 * @author Rikin Patel
 *
 */
public class UserService {

	public Users createUser(ServletContext context, Users user) {
		SessionFactory sessionFactory = (SessionFactory) context.getAttribute("SessionFactory");
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		try{
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
		return user;
	}
	
	@SuppressWarnings("unchecked")
	public List<Users> getUsersByUserId(ServletContext context, long userId){
		
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
}
