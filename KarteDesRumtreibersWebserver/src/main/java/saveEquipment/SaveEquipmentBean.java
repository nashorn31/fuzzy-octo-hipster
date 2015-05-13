package saveEquipment;

import hibernateentitysets.RoomEquipment;
import hibernateentitysets.Rooms;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;
import javax.persistence.EntityManager;

import room.RoomSearch;
import databaseconnection.InitEntityManager;

@ManagedBean(name = "saveEquipmentBean")
@ViewScoped
public class SaveEquipmentBean {

	private String roomName;

	private String equipement;

	private double amount;

	private String message;

	private Map<String, String> possibleEquipement;

	@PostConstruct
	public void init() {
		setPossibleEquipement(new HashMap<String, String>());
		message = "";
		amount = 1.0;

		possibleEquipement.put("Beamer", "Beamer");
		possibleEquipement.put("Tafel", "Tafel");
		possibleEquipement.put("Flipboard", "Flipboard");
		possibleEquipement.put("Visualizer", "Visualizer");
		possibleEquipement.put("Whiteboard", "Whiteboard");
		possibleEquipement.put("Projektor", "Projektor");

	}

	public void saveEquipment(ActionEvent actionEvent) {

		List<Rooms> rooms = RoomSearch.getRoomByNumber(roomName);

		if (rooms.size() == 1) {

			RoomEquipment roomEquipment = new RoomEquipment();
			roomEquipment.setEquipement(equipement);
			roomEquipment.setAmount(amount);
			roomEquipment.setRoom(rooms.get(0));

			message = "Ausrüstung " + equipement + " für Raum " + roomName
					+ " wurde gespeichert";

			EntityManager em = InitEntityManager.getEntityManager();

			em.getTransaction().begin();
			em.persist(roomEquipment);
			em.getTransaction().commit();

		} else {
			message = "Ausrüstung konnte nicht gespeichert werden.";
		}
	}

	public String getRoomName() {
		return roomName;
	}

	public void setRoomName(String roomName) {
		this.roomName = roomName;
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

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Map<String, String> getPossibleEquipement() {
		return possibleEquipement;
	}

	public void setPossibleEquipement(Map<String, String> possibleEquipement) {
		this.possibleEquipement = possibleEquipement;
	}

}
