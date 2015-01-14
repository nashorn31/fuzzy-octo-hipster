package hibernateentitysets;

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


@Entity
@Table(catalog = "WLAN", name = "roomEquipement")
public class RoomEquipment  implements XMLParseable{

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "EquipementName")
	private String equipement;

	@Column(name = "Amount")
	private double amount;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "RoomID", nullable = false)
	private Rooms room;

	public RoomEquipment() {

	}

	public RoomEquipment(int id, String equipement, double amount) {
		this.id = id;
		this.equipement = equipement;
		this.amount = amount;

	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEquipement() {
		return equipement;
	}

	public void setEquipement(String equipement) {
		this.equipement = equipement;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Document toXML(Document doc, Element rootElement) {

		Element roomEquitement = doc.createElement("RoomEquitement");
		rootElement.appendChild(roomEquitement);

		Element eqID = doc.createElement("RoomEquitementID");
		eqID.appendChild(doc.createTextNode(String.valueOf(this.id)));
		roomEquitement.appendChild(eqID);

		Element amount = doc.createElement("amount");
		amount.appendChild(doc.createTextNode(String.valueOf(this.amount)));
		roomEquitement.appendChild(amount);

		Element equipement = doc.createElement("RoomEquitement");
		equipement.appendChild(doc.createTextNode(this.equipement));
		roomEquitement.appendChild(equipement);

		return doc;
	}

}
