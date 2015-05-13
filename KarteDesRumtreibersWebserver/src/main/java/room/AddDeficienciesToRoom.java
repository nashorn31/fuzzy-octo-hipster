package room;

import hibernateentitysets.Deficiencies;
import hibernateentitysets.Rooms;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import databaseconnection.InitEntityManager;
import eMail.SendEmail;

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

		List<Rooms> rooms = RoomSearch.getRoomsWithAttributes(null, null, null,
				null, roomNumber, null, null, null, null, null, null, null);

		if (rooms.isEmpty()) {

			return false;
		} else {

			Deficiencies deficiency = new Deficiencies();
			deficiency.setCategory(category);
			deficiency.setDescription(description);
			deficiency.setReportingUser(reportingUser);
			deficiency.setStatus("OPEN");
			deficiency.setDate(new Date());
			deficiency.setRoom(rooms.get(0));

			EntityManager em = InitEntityManager.getEntityManager();

			em.getTransaction().begin();
			em.persist(deficiency);
			em.getTransaction().commit();

			// Mail to Pikenhan
			SendEmail.sendDeficiencyMail(deficiency);

		}

		return true;
	}

}
