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
	 * try to write a deficiency to the database and write an email to the in
	 * the properties configured email address
	 * 
	 * @param category
	 *            category of the deficiency
	 * @param description
	 *            description of the deficiency
	 * @param reportingUser
	 *            user who reported the deficiency
	 * @param roomNumber
	 *            room number where the deficiency is reported (not the room
	 *            ID!)
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

			SendEmail.sendDeficiencyMail(deficiency);

		}

		return true;
	}

}
