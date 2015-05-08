package ticketSystem;

import hibernateentitysets.Deficiencies;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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

	private String statusFilter;
	private String ticketID;
	private String roomNumber;

	private Date startDate;
	private Date endDate;

	public String getTicketID() {
		return ticketID;
	}

	public void setTicketID(String ticketID) {
		this.ticketID = ticketID;
	}

	public String getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}

	public void onFilterChangedStatus() {

		setDeficiencies(service.getAllTickets(statusFilter, ticketID,
				roomNumber, startDate, endDate));

	}

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
		setDeficiencies(service.getAllTickets(null, null, null, null, null));
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
		EntityManager em = InitEntityManager.getEntityManager();
		em.getTransaction().begin();
		em.merge(deficiencies);
		em.getTransaction().commit();

	}

	/**
	 * Liste Stati für Anzeige des Status
	 */
	public Map<String, String> getStati() {
		Map<String, String> stati = new TreeMap<String, String>();
		stati.put("Offen", "OPEN");
		stati.put("In Arbeit", "IN WORK");
		stati.put("Geschlossen", "CLOSED");
		return stati;
	}

	/**
	 * Liste Stati für Filter
	 */
	public Map<String, String> getStatiAll() {
		Map<String, String> stati = new TreeMap<String, String>();
		stati.put("Alle", "ALL");
		stati.put("Offen", "OPEN");
		stati.put("In Arbeit", "IN WORK");
		stati.put("Geschlossen", "CLOSED");

		return stati;
	}

	public String getStatusFilter() {
		return statusFilter;
	}

	public void setStatusFilter(String statusFilter) {
		this.statusFilter = statusFilter;

	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

}
