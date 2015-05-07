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

	public List<Deficiencies> getAllTickets() {

		TypedQuery<Deficiencies> searchQuery = InitEntityManager
				.getEntityManager().createQuery("FROM Deficiencies ",
						Deficiencies.class);

		searchQuery.setMaxResults(10);

		return searchQuery.getResultList();
	}

	public List<Deficiencies> getAllTickets(String status) {

		TypedQuery<Deficiencies> searchQuery = InitEntityManager
				.getEntityManager().createQuery(
						"FROM Deficiencies WHERE status = '" + status + "'",
						Deficiencies.class);

		searchQuery.setMaxResults(10);

		return searchQuery.getResultList();

	}
}
