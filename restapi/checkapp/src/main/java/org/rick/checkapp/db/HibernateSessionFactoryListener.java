/**
 * 
 */
package org.rick.checkapp.db;

/**
 * @author Rikin Patel
 *
 */
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.hibernate4.encryptor.HibernatePBEEncryptorRegistry;

@WebListener
public class HibernateSessionFactoryListener implements ServletContextListener {

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    	SessionFactory sessionFactory = (SessionFactory) servletContextEvent.getServletContext().getAttribute("SessionFactory");
    	if(sessionFactory != null && !sessionFactory.isClosed()){
    		System.out.println("Closing sessionFactory");
    		sessionFactory.close();
    	}
    	System.out.println("Released Hibernate sessionFactory resource");
    }

    public void contextInitialized(ServletContextEvent servletContextEvent) {
    	Configuration configuration = new Configuration();
    	configuration.configure("hibernate.cfg.xml");
    	System.out.println("Hibernate Configuration created successfully");
    	
    	ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
    	System.out.println("ServiceRegistry created successfully");
    	SessionFactory sessionFactory = configuration
				.buildSessionFactory(serviceRegistry);
    	System.out.println("SessionFactory created successfully");
    	
    	servletContextEvent.getServletContext().setAttribute("SessionFactory", sessionFactory);
    	System.out.println("Hibernate SessionFactory Configured successfully");
    	
    	StandardPBEStringEncryptor strongEncryptor = new StandardPBEStringEncryptor();
        strongEncryptor.setPassword("CheckApp$123!");
        HibernatePBEEncryptorRegistry registry = HibernatePBEEncryptorRegistry.getInstance();
        registry.registerPBEStringEncryptor("checkappStringEncryptor", strongEncryptor);
    }
	
}
