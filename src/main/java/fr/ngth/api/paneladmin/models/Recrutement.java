package fr.ngth.api.paneladmin.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Recrutement {
	
	@Id
	private Long id;

	@Column(name = "chaman")
	private String chaman;
	
	@Column(name = "chasseur")
	private String chasseur;
	
	@Column(name = "demoniste")
	private String demoniste;
	
	@Column(name = "druide")
	private String druide;
	
	@Column(name = "guerrier")
	private String guerrier;
	
	@Column(name = "mage")
	private String mage;
	
	@Column(name = "pretre")
	private String pretre;
	
	@Column(name = "voleur")
	private String voleur;
	
	public Recrutement() {}
	
	public Recrutement(long id, String chaman, String chasseur, String demoniste, String druide, String guerrier,
			String mage, String pretre, String voleur) {
		this.id = id;
		this.chaman = chaman;
		this.chasseur = chasseur;
		this.demoniste = demoniste;
		this.druide = druide;
		this.guerrier = guerrier;
		this.mage = mage;
		this.pretre = pretre;
		this.voleur = voleur;
	}

	public String getChaman() {
		return chaman;
	}

	public void setChaman(String chaman) {
		this.chaman = chaman;
	}

	public String getChasseur() {
		return chasseur;
	}

	public void setChasseur(String chasseur) {
		this.chasseur = chasseur;
	}

	public String getDemoniste() {
		return demoniste;
	}

	public void setDemoniste(String demoniste) {
		this.demoniste = demoniste;
	}

	public String getDruide() {
		return druide;
	}

	public void setDruide(String druide) {
		this.druide = druide;
	}

	public String getGuerrier() {
		return guerrier;
	}

	public void setGuerrier(String guerrier) {
		this.guerrier = guerrier;
	}

	public String getMage() {
		return mage;
	}

	public void setMage(String mage) {
		this.mage = mage;
	}

	public String getPretre() {
		return pretre;
	}

	public void setPretre(String pretre) {
		this.pretre = pretre;
	}

	public String getVoleur() {
		return voleur;
	}

	public void setVoleur(String voleur) {
		this.voleur = voleur;
	}


}
