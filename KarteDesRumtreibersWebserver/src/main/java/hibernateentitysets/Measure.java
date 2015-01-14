package hibernateentitysets;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import xml.XMLParseable;

//TODO
//Eine Messung braucht mehrere Einträge zu den verfügbaren Accespoints
//Datenbanktabelle anlegen!! Generell das Datenbankschema gescheit erstellen

@Entity
@Table(name = "Meassures")
public class Measure implements XMLParseable {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "meassurePointId")
	private int meassurePointId;

	@Column(name = "accessPointId")
	private int accessPointId;

	@Column(name = "time")
	private Date time;

	@Column(name = "frequency")
	private int frequency;

	@Column(name = "level")
	private int level;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMeassurePointsId() {
		return meassurePointId;
	}

	public void setMeassurePointsId(int meassurePointsId) {
		this.meassurePointId = meassurePointsId;
	}

	public int getAccessPointsId() {
		return accessPointId;
	}

	public void setAccessPointsId(int accessPointsId) {
		this.accessPointId = accessPointsId;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public int getFrequency() {
		return frequency;
	}

	public void setFrequency(int frequency) {
		this.frequency = frequency;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	@Override
	public Document toXML(Document doc, Element rootElement) {

		Element messure = doc.createElement("Messure");
		rootElement.appendChild(messure);

		// messureID element
		Element messureID = doc.createElement("messureID");
		messureID.appendChild(doc.createTextNode(String.valueOf(this.id)));
		messure.appendChild(messureID);

		// element
		Element meassurePointId = doc.createElement("meassurePointsId");
		meassurePointId.appendChild(doc.createTextNode(String
				.valueOf(this.meassurePointId)));
		messure.appendChild(meassurePointId);

		return null;
	}

}
