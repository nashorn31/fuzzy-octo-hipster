package ticketSystem;

import hibernateentitysets.Deficiencies;

import java.io.Serializable;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;

import databaseconnection.InitEntityManager;

@ManagedBean(name = "ticketBasicView")
@ViewScoped
public class TicketGuiBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Deficiencies> deficiencies;

	@ManagedProperty("#{ticketService}")
	private ValuesForTicket service;

	public ValuesForTicket getService() {
		return service;
	}

	public void setService(ValuesForTicket service) {
		this.service = service;
	}

	@PostConstruct
	public void init() {
		setDeficiencies(service.getAllTickets());
	}

	public List<Deficiencies> getDeficiencies() {
		return deficiencies;
	}

	public void setDeficiencies(List<Deficiencies> deficiencies) {
		this.deficiencies = deficiencies;
	}

	public void changeStatus(Deficiencies deficiencies, String status) {
		deficiencies.setStatus(status);

	}

	public void changeStatus(Deficiencies deficiencies) {
		System.out.println(deficiencies.getId() + " "
				+ deficiencies.getStatus());
		EntityManager em = InitEntityManager.getEntityManager();
		em.getTransaction().begin();
		em.merge(deficiencies);
		em.getTransaction().commit();

	}

	public Map<String, String> getStati() {
		Map<String, String> stati = new HashMap<String, String>();
		stati.put("OPEN", "OPEN");
		stati.put("IN WORK", "IN WORK");
		stati.put("CLOSED", "CLOSED");
		return stati;
	}
}
