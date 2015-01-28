package roomSearch;

import java.util.List;

import hibernateentitysets.Rooms;

import javax.persistence.TypedQuery;

import databaseconnection.InitEntityManager;

public class RoomSearch {

	public static List<Rooms> getRoomByName(String roomName, String roomType,
			String wing, String floor, String roomNumber,
			String personCapacity, String beameRequired) {

		String whereClause = "WHERE 1=1";

		if (roomName != null) {
			whereClause = whereClause + " AND roomName ='" + roomName + "%'";
		}
		if (roomType != null) {
			whereClause = whereClause + " AND roomType ='" + roomType + "'";
		}
		if (wing != null) {
			whereClause = whereClause + " AND wing ='" + wing + "'";
		}
		if (floor != null && !floor.equals("-1")) {
			whereClause = whereClause + " AND floor =" + floor;
		}
		if (roomNumber != null) {
			whereClause = whereClause + " AND roomNumber ='" + roomNumber + "'";
		}
		if (personCapacity != null && !personCapacity.equals("-1")) {
			whereClause = whereClause + " AND personCapacity >"
					+ personCapacity;
		}

		TypedQuery<Rooms> searchQuery = InitEntityManager.getEntityManager()
				.createQuery("FROM Rooms " + whereClause, Rooms.class);

		searchQuery.setMaxResults(10);

		return searchQuery.getResultList();

	}
}
