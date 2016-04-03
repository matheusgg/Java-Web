package app.jpa.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerProvider {
	private static final EntityManagerFactory entityManagerFactory = buildEntityManagerFactory();
	
	private static EntityManagerFactory buildEntityManagerFactory(){
		return Persistence.createEntityManagerFactory("jpa_unit");
	}
	
	public static boolean closeEntityManager(EntityManager manager){
		boolean isClosed = false;
		try{
			manager.close();
			isClosed = true;
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return isClosed;
	}

	public static EntityManagerFactory getEntitymanagerfactory() {
		return entityManagerFactory;
	}
}
