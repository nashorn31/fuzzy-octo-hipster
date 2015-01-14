package serverJobs;

import hibernateentitysets.Rooms;

import java.util.List;
import javax.persistence.EntityManager;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import databaseconnection.InitEntityManager;

/**
 * 
 * @author ZIMM051
 * 
 */
public class StartListener  implements ServletContextListener {

	/**
	 * executed when the Server shuts down
	 */
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// close the EntityManager for DB access
		InitEntityManager.closeEntityManager();

	}

	/**
	 * executed when the Server starts
	 */
	@Override
	public void contextInitialized(ServletContextEvent arg0) {

		InitEntityManager.initalEntityManagerFactorys();

		System.out.println("PENIS");
		
		EntityManager em = InitEntityManager.getEntityManager();
		List<Rooms> rooms = em.createQuery("From Rooms", Rooms.class)
				.getResultList();
		System.out.println(rooms.get(0).getRoomID());
	}

}
