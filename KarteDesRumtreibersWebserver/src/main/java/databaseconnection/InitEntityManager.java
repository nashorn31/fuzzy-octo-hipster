package databaseconnection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Manage and create the EntityManager for the DB connection
 * 
 * @author Johannes
 */
public class InitEntityManager {

	private static EntityManagerFactory entityManagerFactory;

	/**
	 * Get the EntityManager
	 * 
	 * @return
	 */
	public static EntityManager getEntityManager() {

		/*
		 * If the EntityManager is still null or closed (should not happen
		 * because the EntityManager is initialised during the tomcat start)
		 */
		if (InitEntityManager.entityManagerFactory == null
				|| !InitEntityManager.entityManagerFactory.isOpen()) {

			/*
			 * initialize the EntityManager by loading the persistence
			 */
			InitEntityManager.createEntityManagerFactory();
		}

		return InitEntityManager.entityManagerFactory.createEntityManager();
	}

	/**
	 * close the EntityManager
	 */
	public static void closeEntityManager() {
		// close for the research
		InitEntityManager.entityManagerFactory.close();

	}

	/**
	 * create the EntityManagerFactory with the configuration from the
	 * persistence.xml
	 */
	public static void createEntityManagerFactory() {
		InitEntityManager.entityManagerFactory = Persistence
				.createEntityManagerFactory("Database");

	}

}
