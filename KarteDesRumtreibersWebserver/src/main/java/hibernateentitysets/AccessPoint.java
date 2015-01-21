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
@Table(name = "Meassures")
public class AccessPoint implements XMLParseable {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "bssid")
	private String bssid;

	@Column(name = "Standort")
	private String standort;

	@Column(name = "xPos")
	private int xPos;

	@Column(name = "yPos")
	private int yPos;

	@Column(name = "zPos")
	private int zPos;

	@Column(name = "capabilities")
	private String capabilities;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "accesPoint")
	private Set<MeasureRecivedAccessPoint> measureRecivedAccessPoint;

	@Override
	public Document toXML(Document doc, Element rootElement) {

		Element accessPoint = doc.createElement("accessPoint");
		rootElement.appendChild(accessPoint);

		Element accessPointID = doc.createElement("accessPointID");
		accessPointID.appendChild(doc.createTextNode(String.valueOf(this.id)));
		accessPoint.appendChild(accessPointID);

		Element bssid = doc.createElement("bssid");
		bssid.appendChild(doc.createTextNode(this.bssid));
		accessPoint.appendChild(bssid);

		Element standort = doc.createElement("standort");
		standort.appendChild(doc.createTextNode(this.standort));
		accessPoint.appendChild(standort);

		Element xPos = doc.createElement("xPos");
		xPos.appendChild(doc.createTextNode(String.valueOf(this.xPos)));
		accessPoint.appendChild(xPos);

		Element yPos = doc.createElement("yPos");
		yPos.appendChild(doc.createTextNode(String.valueOf(this.yPos)));
		accessPoint.appendChild(yPos);

		Element zPos = doc.createElement("zPos");
		zPos.appendChild(doc.createTextNode(String.valueOf(this.zPos)));
		accessPoint.appendChild(zPos);

		Element capabilities = doc.createElement("capabilities");
		capabilities.appendChild(doc.createTextNode(this.capabilities));
		accessPoint.appendChild(capabilities);

		for (MeasureRecivedAccessPoint measure : measureRecivedAccessPoint) {
			measure.toXML(doc, accessPoint);
		}

		return doc;
	}

}
