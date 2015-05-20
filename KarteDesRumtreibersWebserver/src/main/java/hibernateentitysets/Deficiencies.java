package hibernateentitysets;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import xml.XMLParseable;

/**
 * Data Access Object to access the database table "deficiencies"
 * @author Johannes
 *
 */
@Entity
@Table(catalog = "WLAN", name = "deficiencies")
public class Deficiencies implements XMLParseable, Serializable {

	/**
	 * default serializable Id
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "description")
	private String description;

	@Column(name = "status")
	private String status;

	@Column(name = "reportingUser")
	private String reportingUser;

	@Column(name = "category")
	private String category;

	@Column(name = "createDate", nullable = false)
	private Date date;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "RoomID", nullable = false)
	private Rooms room;

	public Rooms getRoom() {
		return room;
	}

	public void setRoom(Rooms room) {
		this.room = room;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getReportingUser() {
		return reportingUser;
	}

	public void setReportingUser(String reportingUser) {
		this.reportingUser = reportingUser;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	
	/**
	 * Format the data of a specific format
	 * @return the date in the format: dd.MM.yyyy
	 */
	public String getDateString() {
		SimpleDateFormat dateformat = new SimpleDateFormat("dd.MM.yyyy");
		return dateformat.format(getDate());
	}

	public Document toXML(Document doc, Element rootElement) {
		Element deficiencie = doc.createElement("Deficiency");
		rootElement.appendChild(deficiencie);

		Element eqID = doc.createElement("DeficiencyID");
		eqID.appendChild(doc.createTextNode(String.valueOf(this.id)));
		deficiencie.appendChild(eqID);

		Element description = doc.createElement("description");
		description.appendChild(doc.createTextNode(this.description));
		deficiencie.appendChild(description);

		Element category = doc.createElement("category");
		category.appendChild(doc.createTextNode(this.category));
		deficiencie.appendChild(category);

		Element status = doc.createElement("status");
		status.appendChild(doc.createTextNode(this.status));
		deficiencie.appendChild(status);

		return doc;
	}
}
