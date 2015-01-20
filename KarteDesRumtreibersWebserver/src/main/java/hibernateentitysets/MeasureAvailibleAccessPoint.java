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
public class MeasureAvailibleAccessPoint implements XMLParseable {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "measureId")
	private int measureId;
	
	@Column(name ="bssid")
	private String bssid;

	@Column(name = "frequency")
	private int frequency;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "measureId", nullable = false)
	private Measure measure;

	@Override
	public Document toXML(Document doc, Element rootElement) {
		// TODO Auto-generated method stub
		return null;
	}


}
