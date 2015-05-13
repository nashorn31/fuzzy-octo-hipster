package ticketSystem;

import hibernateentitysets.Deficiencies;
import hibernateentitysets.Rooms;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.persistence.TypedQuery;

import room.RoomSearch;
import databaseconnection.InitEntityManager;

@ManagedBean(name = "ticketService")
@ApplicationScoped
public class ValuesForTicket {

	public List<Deficiencies> getAllTickets(String status, String ticketID,
			String roomNumber, Date startDate, Date endDate) {

		String where = "Where 1=1";

		if (status != null && !status.equals("ALL")) {
			where = where + " AND status= '" + status + "'";
		}

		if (ticketID != null && !ticketID.equals("")) {
			where = where + " AND id= '" + ticketID + "'";
		}

		if (roomNumber != null && !roomNumber.equals("")) {

			List<Rooms> rooms = RoomSearch.getRoomsWithAttributes(null, null,
					null, null, roomNumber, null, null, null, null, null, null,
					null);

			if (!rooms.isEmpty()) {
				where = where + " AND RoomID= '" + rooms.get(0).getRoomID()
						+ "'";
			} else {
				where = where + " AND 1=2";
			}
		}

		if (startDate != null) {
			SimpleDateFormat dateformat = new SimpleDateFormat();
			dateformat.applyPattern("yyyy-MM-dd");
			where = where + " AND createDate >= '"
					+ dateformat.format(startDate) + "'";
		}

		if (endDate != null) {
			SimpleDateFormat dateformat = new SimpleDateFormat();
			dateformat.applyPattern("yyyy-MM-dd");
			where = where + " AND createDate <= '" + dateformat.format(endDate)
					+ "'";
		}

		TypedQuery<Deficiencies> searchQuery = InitEntityManager
				.getEntityManager().createQuery("FROM Deficiencies " + where,
						Deficiencies.class);

		searchQuery.setMaxResults(500);

		return searchQuery.getResultList();

	}
}
