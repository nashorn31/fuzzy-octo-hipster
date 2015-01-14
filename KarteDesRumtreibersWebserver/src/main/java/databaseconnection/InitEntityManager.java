package databaseconnection;

import hibernateentitysets.Rooms;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Manage and create the EntityManager for the DB connection
 * 
 * @author zimm051
 * 
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
			InitEntityManager.initalEntityManagerFactorys();
		}

		return InitEntityManager.entityManagerFactory.createEntityManager();
	}

	/**
	 * close the two EntityManagers
	 */
	public static void closeEntityManager() {
		// close for the research
		InitEntityManager.entityManagerFactory.close();

	}

	public static void initalEntityManagerFactorys() {
		InitEntityManager.entityManagerFactory = Persistence
				.createEntityManagerFactory("Database");


	}

}
