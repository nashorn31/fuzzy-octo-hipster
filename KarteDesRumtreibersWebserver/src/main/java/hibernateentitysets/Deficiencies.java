package hibernateentitysets;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(catalog = "WLAN", name = "deficiencies")
public class Deficiencies {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "roomID")
	private int roomID;

	@Column(name = "responsibleUserID")
	private int responsibleUserID;

	@Column(name = "description")
	private String description;

	@Column(name = "status")
	private int status;

	@Column(name = "reporterUserID")
	private int reporterUserID;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getRoomID() {
		return roomID;
	}

	public void setRoomID(int roomID) {
		this.roomID = roomID;
	}

	public int getResponsibleUserID() {
		return responsibleUserID;
	}

	public void setResponsibleUserID(int responsibleUserID) {
		this.responsibleUserID = responsibleUserID;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getReporterUserID() {
		return reporterUserID;
	}

	public void setReporterUserID(int reporterUserID) {
		this.reporterUserID = reporterUserID;
	}

}
