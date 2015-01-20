package hibernateentitysets;

import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import xml.XMLParseable;

@Entity
@Table(name = "Meassures")
public class Measure implements XMLParseable {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "measurePointsId")
	private int measurePointsId;

	@Column(name = "time")
	private Date time;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "measurePointsId", nullable = false)
	private MeasurePoint measurePoint;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "measure")
	private Set<MeasureAvailibleAccessPoint> measureAccessPoint;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
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
				.valueOf(this.measurePointsId)));
		messure.appendChild(meassurePointId);

		return null;
	}

}
