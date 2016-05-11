package mm.utils;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;


public class HibernateSingleton {

	private static HibernateSingleton instance = null;
	private StandardServiceRegistry serviceRegistry;
	private static SessionFactory sessionFactory;
	
	private HibernateSingleton(){
		 Configuration configuration = new Configuration();
	        configuration.configure();
	        
         
         serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
	        		configuration.getProperties()).build();
         //logger.info("Hibernate serviceRegistry created");
	        
	     sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	}
	
	public static HibernateSingleton getInstance(){
		if(instance == null){
            instance  = new HibernateSingleton();
        }
        return instance;
	}
	
	   public static SessionFactory getSessionFactory() {
	        return sessionFactory;
	   }
}
