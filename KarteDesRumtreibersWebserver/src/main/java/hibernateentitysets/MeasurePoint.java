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
@Table(catalog = "WLAN", name = "measurePoints")
public class MeasurePoint implements XMLParseable, Serializable {
	
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

	@Column(name = "xPos")
	private double xPos;

	@Column(name = "yPos")
	private double yPos;

	@Column(name = "zPos")
	private double zPos;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "measurePoint")
	private Set<Measure> measures;

	public Document toXML(Document doc, Element rootElement) {

		Element messurePoint = doc.createElement("messurePoint");
		rootElement.appendChild(messurePoint);

		// messureID element
		Element messurePointID = doc.createElement("messurePointID");
		messurePointID.appendChild(doc.createTextNode(String.valueOf(this.id)));
		messurePoint.appendChild(messurePointID);

		// element
		Element description = doc.createElement("description");
		description.appendChild(doc.createTextNode(this.description));
		messurePoint.appendChild(description);

		Element xPos = doc.createElement("xPos");
		xPos.appendChild(doc.createTextNode(String.valueOf(this.xPos)));
		messurePoint.appendChild(xPos);

		Element yPos = doc.createElement("yPos");
		yPos.appendChild(doc.createTextNode(String.valueOf(this.yPos)));
		messurePoint.appendChild(yPos);

		Element zPos = doc.createElement("zPos");
		zPos.appendChild(doc.createTextNode(String.valueOf(this.zPos)));
		messurePoint.appendChild(zPos);

		return doc;
	}
}
