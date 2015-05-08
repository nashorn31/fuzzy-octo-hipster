package hibernateentitysets;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import xml.XMLParseable;

@Entity
@Table(catalog = "WLAN", name = "rooms")
public class Rooms implements XMLParseable, Serializable {

	/**
	 * default serializable Id
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int roomID;

	@Column(name = "name")
	private String name;

	@Column(name = "type")
	private String type;

	@Column(name = "wing")
	private String wing;

	@Column(name = "floor")
	private String floor;

	@Column(name = "roomnumber")
	private String roomnumber;

	@Column(name = "personNumber")
	private int personNumber;

	@Column(name = "doorXPos")
	private double doorXPos;

	@Column(name = "doorYPos")
	private double doorYPos;

	@Column(name = "doorZPos")
	private double doorZPos;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "room")
	private Set<RoomEquipment> roomEquipments;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "room")
	private Set<Deficiencies> roomDeficiencies;

	public int getRoomID() {
		return roomID;
	}

	public void setRoomID(int roomID) {
		this.roomID = roomID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getWing() {
		return wing;
	}

	public void setWing(String wing) {
		this.wing = wing;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public String getRoomnumber() {
		return roomnumber;
	}

	public void setRoomnumber(String roomnumber) {
		this.roomnumber = roomnumber;
	}

	public int getPersonNumber() {
		return personNumber;
	}

	public void setPersonNumber(int personNumber) {
		this.personNumber = personNumber;
	}

	public double getDoorXPos() {
		return doorXPos;
	}

	public void setDoorXPos(double doorXPos) {
		this.doorXPos = doorXPos;
	}

	public double getDoorYPos() {
		return doorYPos;
	}

	public void setDoorYPos(double doorYPos) {
		this.doorYPos = doorYPos;
	}

	public double getDoorZPos() {
		return doorZPos;
	}

	public void setDoorZPos(double doorZPos) {
		this.doorZPos = doorZPos;
	}

	public Set<RoomEquipment> getRoomEquipments() {
		return roomEquipments;
	}

	public void setRoomEquipments(Set<RoomEquipment> roomEquipments) {
		this.roomEquipments = roomEquipments;
	}

	public Document toXML(Document doc, Element rootElement) {

		Element room = doc.createElement("room");
		rootElement.appendChild(room);

		// roomID element
		Element roomID = doc.createElement("roomID");
		roomID.appendChild(doc.createTextNode(String.valueOf(this.roomID)));
		room.appendChild(roomID);

		// roomName element
		Element roomName = doc.createElement("name");
		roomName.appendChild(doc.createTextNode(this.name));
		room.appendChild(roomName);

		// wing element
		Element wing = doc.createElement("wing");
		wing.appendChild(doc.createTextNode(this.wing));
		room.appendChild(wing);

		// floor elements
		Element floor = doc.createElement("floor");
		floor.appendChild(doc.createTextNode(this.floor));
		room.appendChild(floor);

		// floor elements
		Element roomnumber = doc.createElement("roomnumber");
		roomnumber.appendChild(doc.createTextNode(this.roomnumber));
		room.appendChild(roomnumber);

		// PersonNumber
		Element personNumber = doc.createElement("personNumber");
		personNumber.appendChild(doc.createTextNode(String
				.valueOf(this.personNumber)));
		room.appendChild(personNumber);

		// Door position X
		Element doorXPos = doc.createElement("doorXPos");
		doorXPos.appendChild(doc.createTextNode(String.valueOf(this.doorXPos)));
		room.appendChild(doorXPos);

		// Door position Y
		Element doorYPos = doc.createElement("doorYPos");
		doorYPos.appendChild(doc.createTextNode(String.valueOf(this.doorYPos)));
		room.appendChild(doorYPos);

		// Door position Z
		Element doorZPos = doc.createElement("doorZPos");
		doorZPos.appendChild(doc.createTextNode(String.valueOf(this.doorZPos)));
		room.appendChild(doorZPos);

		// Equipment Elements
		Element roomEquitements = doc.createElement("RoomEquipments");
		room.appendChild(roomEquitements);

		for (RoomEquipment re : roomEquipments) {
			re.toXML(doc, roomEquitements);
		}

		// Deficiencies Elements
		Element deficiencies = doc.createElement("Deficiencies");
		room.appendChild(deficiencies);
		for (Deficiencies de : roomDeficiencies) {
			de.toXML(doc, deficiencies);
		}

		return doc;

	}
}