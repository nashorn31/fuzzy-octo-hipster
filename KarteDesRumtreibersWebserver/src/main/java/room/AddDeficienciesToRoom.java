package room;

import hibernateentitysets.Deficiencies;
import hibernateentitysets.Rooms;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import databaseconnection.InitEntityManager;

public class AddDeficienciesToRoom {

	/**
	 * 
	 * @param category
	 * @param description
	 * @param reportingUser
	 * @param roomNumber
	 * @return Return true, if the deficiency is added successfully
	 */
	public static boolean addDeficiency(String category, String description,
			String reportingUser, String roomNumber) {

		List<Rooms> rooms = RoomSearch.getRoomByName(null, null, null, null,
				roomNumber, null, null);

		if (rooms.isEmpty()) {

			return false;
		} else {

			Deficiencies deficiencies = new Deficiencies();
			deficiencies.setCategory(category);
			deficiencies.setDescription(description);
			deficiencies.setReportingUser(reportingUser);
			deficiencies.setStatus("OPEN");
			// deficiencies.setDate(new Date());
			deficiencies.setRoom(rooms.get(0));

			EntityManager em = InitEntityManager.getEntityManager();

			em.getTransaction().begin();
			em.persist(deficiencies);
			em.getTransaction().commit();

		}

		return true;
	}

}
