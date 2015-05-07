package ticketSystem;

import hibernateentitysets.Deficiencies;

import java.util.List;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.persistence.TypedQuery;

import databaseconnection.InitEntityManager;

@ManagedBean(name = "ticketService")
@ApplicationScoped
public class ValuesForTicket {

	public List<Deficiencies> getAllTickets(String status, String ticketID,
			String roomNumber) {

		String where = "Where 1=1";

		if (status != null && !status.equals("ALL")) {
			where = where + " AND status= '" + status + "'";
		}

		if (ticketID != null && !ticketID.equals("")) {
			where = where + " AND id= '" + ticketID + "'";
		}

		if (roomNumber != null && !roomNumber.equals("")) {

			where = where + " AND RoomID= '" + roomNumber + "'";

		}

		TypedQuery<Deficiencies> searchQuery = InitEntityManager
				.getEntityManager().createQuery("FROM Deficiencies " + where,
						Deficiencies.class);

		searchQuery.setMaxResults(10);

		return searchQuery.getResultList();

	}
}
