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
@Table(name = "Meassures")
public class MeasureRecivedAccessPoint implements XMLParseable {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "measureID")
	private int measureID;

	@Column(name = "level")
	private int level;

	@Column(name = "accesPointId", insertable = false, updatable = false)
	private int accesPointId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "measureId", nullable = false)
	private Measure measure;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "accesPointId", nullable = false)
	private AccessPoint accesPoint;

	public Document toXML(Document doc, Element rootElement) {

		Element measureRecivedAccessPoint = doc
				.createElement("measureRecivedAccessPoint");
		rootElement.appendChild(measureRecivedAccessPoint);

		Element measureRecivedAccessPointID = doc
				.createElement("accessPointID");
		measureRecivedAccessPointID.appendChild(doc.createTextNode(String
				.valueOf(this.id)));
		measureRecivedAccessPoint.appendChild(measureRecivedAccessPointID);

		Element measureID = doc.createElement("measureID");
		measureID
				.appendChild(doc.createTextNode(String.valueOf(this.measureID)));
		measureRecivedAccessPoint.appendChild(measureID);

		Element level = doc.createElement("level");
		level.appendChild(doc.createTextNode(String.valueOf(this.level)));
		measureRecivedAccessPoint.appendChild(level);

		Element accesPointId = doc.createElement("accesPointId");
		accesPointId.appendChild(doc.createTextNode(String
				.valueOf(this.accesPointId)));
		measureRecivedAccessPoint.appendChild(accesPointId);

		return doc;
	}

}
