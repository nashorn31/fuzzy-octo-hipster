package hibernateentitysets;

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
public class Rooms implements XMLParseable {

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
	private int roomnumber;

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

	public Rooms() {
	}

	public Rooms(int roomID, String partOfBuilding, int personNumber,
			Set<RoomEquipment> roomEquipments) {

		this.roomID = roomID;
		this.wing = partOfBuilding;
		this.personNumber = personNumber;
		this.roomEquipments = roomEquipments;
	}

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

	public int getRoomnumber() {
		return roomnumber;
	}

	public void setRoomnumber(int roomnumber) {
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

		// roomID elements
		Element roomID = doc.createElement("roomID");
		roomID.appendChild(doc.createTextNode(String.valueOf(this.roomID)));
		room.appendChild(roomID);

		// PartOfBuilding
		Element partOfBuilding = doc.createElement("partOfBuilding");
		partOfBuilding
				.appendChild(doc.createTextNode(String.valueOf(this.wing)));
		room.appendChild(partOfBuilding);

		// PersonNumber
		Element personNumber = doc.createElement("personNumber");
		personNumber.appendChild(doc.createTextNode(String
				.valueOf(this.personNumber)));
		room.appendChild(personNumber);

		// Equipment Elements
		Element roomEquitements = doc.createElement("RoomEquitements");
		room.appendChild(roomEquitements);

		for (RoomEquipment re : roomEquipments) {
			re.toXML(doc, roomEquitements);
		}

		return doc;

	}
}