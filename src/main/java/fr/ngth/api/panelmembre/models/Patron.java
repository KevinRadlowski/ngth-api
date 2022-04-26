package fr.ngth.api.panelmembre.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import fr.ngth.api.login.models.Membre;

@Entity
@Table(name = "patrons")
public class Patron {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@ManyToOne
	@JoinColumn(name = "id_membre")
	private Membre membre;

	@Column(name = "name")
	private String name;

	@Column(name = "url")
	private String url;

	@Column(name = "quality")
	private String quality;
	
	@Column(name = "numberJob")
	private String numberJob;

	public String getNumberJob() {
		return numberJob;
	}

	public void setNumberJob(String numberJob) {
		this.numberJob = numberJob;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Membre getMembre() {
		return membre;
	}

	public void setMembre(Membre membre) {
		this.membre = membre;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getQuality() {
		return quality;
	}

	public void setQuality(String quality) {
		this.quality = quality;
	}

	public Patron() {
	};

	public Patron(Membre membre, String name, String url, String quality, String numberJob) {
		this.membre = membre;
		this.name = name;
		this.url = url;
		this.quality = quality;
		this.numberJob = numberJob;
	}

	@Override
	public String toString() {
		return "Offre [id=" + id + ", membre=" + membre + ", nom=" + name + ", url=" + url
				+ ", qualité=" + quality + "]";
	}

}
