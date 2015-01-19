package roomSearch;

import java.util.List;

import hibernateentitysets.Rooms;

import javax.persistence.TypedQuery;

import databaseconnection.InitEntityManager;

public class RoomSearch {

	public static List<Rooms> getRoomByName(String roomName) {

		TypedQuery<Rooms> searchQuery = InitEntityManager.getEntityManager()
				.createQuery(
						"FROM Rooms WHERE roomnumber like '" + roomName + "%'",
						Rooms.class);

		return searchQuery.getResultList();

	}

	public static List<Rooms> getRoomByName(String roomName, String wing) {

		TypedQuery<Rooms> searchQuery = InitEntityManager.getEntityManager()
				.createQuery(
						"FROM Rooms WHERE roomnumber like '" + roomName
								+ "%' AND wing = '" + wing + "'", Rooms.class);

		return searchQuery.getResultList();

	}
}
