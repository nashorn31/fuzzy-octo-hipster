package room;

import java.util.List;

import hibernateentitysets.RoomEquipment;
import hibernateentitysets.Rooms;

import javax.persistence.TypedQuery;

import databaseconnection.InitEntityManager;

public class RoomSearch {

	public static List<Rooms> getRoomsWithAttributes(String roomName,
			String roomType, String wing, String floor, String roomNumber,
			String personCapacity, String beamerRequired,
			String projectorRepuired, String tafelRepuired,
			String flipboardRepuired, String visualizerRepuired,
			String whiteboardRepuired) {

		String whereClause = "WHERE 1=1";

		if (roomName != null && !roomName.equals("null")) {
			whereClause = whereClause + " AND roomName ='" + roomName + "%'";
		}
		if (roomType != null && !roomType.equals("null")) {
			whereClause = whereClause + " AND roomType ='" + roomType + "'";
		}
		if (wing != null && !wing.equals("null")) {
			whereClause = whereClause + " AND wing ='" + wing + "'";
		}
		if (floor != null && !floor.equals("-1")) {
			whereClause = whereClause + " AND floor =" + floor;
		}
		if (roomNumber != null && !roomName.equals("null")) {
			whereClause = whereClause + " AND roomNumber ='" + roomNumber + "'";
		}
		if (personCapacity != null && !personCapacity.equals("-1")) {
			whereClause = whereClause + " AND personCapacity >"
					+ personCapacity;
		}

		TypedQuery<Rooms> searchQuery = InitEntityManager.getEntityManager()
				.createQuery("FROM Rooms " + whereClause, Rooms.class);

		List<Rooms> rooms = searchQuery.getResultList();

		if (beamerRequired != null && beamerRequired.equals("true")) {
			filterForEquipement(rooms, "Beamer");
		}
		if (tafelRepuired != null && tafelRepuired.equals("true")) {
			filterForEquipement(rooms, "Tafel");
		}
		if (flipboardRepuired != null && flipboardRepuired.equals("true")) {
			filterForEquipement(rooms, "Flipboard");
		}
		if (visualizerRepuired != null && visualizerRepuired.equals("true")) {
			filterForEquipement(rooms, "Visualizer");
		}
		if (whiteboardRepuired != null && whiteboardRepuired.equals("true")) {
			filterForEquipement(rooms, "Whiteboard");
		}
		if (projectorRepuired != null && projectorRepuired.equals("true")) {
			filterForEquipement(rooms, "Projektor");
		}

		return rooms;

	}

	public static List<Rooms> getRoomByNumber(String roomNumber) {

		String whereClause = "WHERE 1=1";

		if (roomNumber != null) {
			whereClause = whereClause + " AND roomNumber ='" + roomNumber + "'";
		}

		TypedQuery<Rooms> searchQuery = InitEntityManager.getEntityManager()
				.createQuery("FROM Rooms " + whereClause, Rooms.class);

		return searchQuery.getResultList();

	}

	private static void filterForEquipement(List<Rooms> rooms, String equipment) {

		for (Rooms room : rooms) {

			boolean containsEquipment = false;

			for (RoomEquipment roomEQ : room.getRoomEquipments()) {

				if (roomEQ.getEquipement().equals(equipment)) {
					containsEquipment = true;
				}
			}
			if (containsEquipment) {
				rooms.remove(room);
			}
		}

	}
}
