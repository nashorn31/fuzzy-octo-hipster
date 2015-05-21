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

/**
 * Bean to display the tickets
 * 
 * @author Johannes
 *
 */
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

	/**
	 * class to load deficiencies provided by the tomcat
	 */
	@ManagedProperty("#{ticketService}")
	private ValuesForTicket service;

	public ValuesForTicket getService() {
		return service;
	}

	public void setService(ValuesForTicket service) {
		this.service = service;
	}

	/**
	 * executed after the bean is created to load the deficiencies
	 */
	@PostConstruct
	public void init() {
		setDeficiencies(service.getAllTickets(null, null, null, null, null));
	}

	/**
	 * reload the deficiencies after a filter is set
	 */
	public void onFilterChangedStatus() {
		setDeficiencies(service.getAllTickets(statusFilter, ticketID,
				roomNumber, startDate, endDate));
	}

	/**
	 * change the status from a specific deficiency
	 * 
	 * @param deficiency
	 *            deficiency on which the status should be changed
	 * @param status
	 *            status to which the should be changed
	 */
	public void changeStatus(Deficiencies deficiency, String status) {
		deficiency.setStatus(status);

	}

	/**
	 * Merge a specific deficiency to the database
	 * 
	 * @param deficiency
	 *            Deficiency which should be merged to the database
	 */
	public void changeStatus(Deficiencies deficiency) {
		EntityManager em = InitEntityManager.getEntityManager();
		em.getTransaction().begin();
		em.merge(deficiency);
		em.getTransaction().commit();

	}

	/**
	 * Merge all changed statuses
	 */
	public void changeAllStatus() {
		EntityManager em = InitEntityManager.getEntityManager();

		for (Deficiencies d : deficiencies) {
			em.getTransaction().begin();
			em.merge(d);
			em.getTransaction().commit();
		}

	}

	/**
	 * list of all statuses needed to the display the deficiencies
	 */
	public Map<String, String> getStati() {
		Map<String, String> stati = new TreeMap<String, String>();
		stati.put("Offen", "OPEN");
		stati.put("In Arbeit", "IN WORK");
		stati.put("Geschlossen", "CLOSED");
		return stati;
	}

	/**
	 * list of all statuses needed for the filter
	 */
	public Map<String, String> getStatiAll() {
		Map<String, String> stati = new TreeMap<String, String>();
		stati.put("Alle", "ALL");
		stati.put("Offen", "OPEN");
		stati.put("In Arbeit", "IN WORK");
		stati.put("Geschlossen", "CLOSED");

		return stati;
	}

	// just the getter and setter to access the variables
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

	public List<Deficiencies> getDeficiencies() {
		return deficiencies;
	}

	public void setDeficiencies(List<Deficiencies> deficiencies) {
		this.deficiencies = deficiencies;
	}

}
