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

@Entity
@Table(catalog = "WLAN", name = "measurePoints")
public class MeasurePoint {

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
}
