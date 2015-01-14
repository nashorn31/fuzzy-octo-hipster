package roomSearch;

import hibernateentitysets.Rooms;

import javax.persistence.TypedQuery;

import databaseconnection.InitEntityManager;

public class RoomSearch {

	public static Rooms getRoomByName(String roomName) {

		TypedQuery<Rooms> searchQuery = InitEntityManager.getEntityManager()
				.createQuery(
						"FROM Rooms WHERE roomnumber like '" + roomName + "%'",
						Rooms.class);

		// set max results for the search
		searchQuery.setMaxResults(5);
		
		return searchQuery.getResultList().get(0);

		// Set<RoomEquipment> roomEQ = new TreeSet<RoomEquipment>();
		//
		// roomEQ.add(new RoomEquipment(1, "Beamer", 1));
		// roomEQ.add(new RoomEquipment(1, "Stifte", 5));
		//
		// return new Rooms(100, "a", 26, roomEQ);

	}
}
