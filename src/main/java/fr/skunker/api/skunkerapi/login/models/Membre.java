package fr.skunker.api.skunkerapi.login.models;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Membre extends User {

	@Column(name = "pseudo")
	private String pseudo;

	@Column(name = "classe")
	private String classe;

	@Column(name = "race")
	private String race;

	@Column(name = "premierMetier")
	private String premierMetier;

	@Column(name = "secondMetier")
	private String secondMetier;

	@Column(name = "level")
	private Long level;
	
	public Membre() {
	}

	public Membre(String pseudo, String username, String email, String password, String classe, String race,
			String premierMetier, String secondMetier, Long level) {
		super(username, email, password);
		this.pseudo = pseudo;
		this.classe = classe;
		this.race = race;
		this.premierMetier = premierMetier;
		this.secondMetier = secondMetier;
		this.level = level;
	}

	public Membre(Long id, String pseudo, String username, String email, String password, String classe, String race,
			String premierMetier, String secondMetier, Long level) {
		super(id, username, email, password);
		this.pseudo = pseudo;
		this.classe = classe;
		this.race = race;
		this.premierMetier = premierMetier;
		this.secondMetier = secondMetier;
		this.level = level;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getClasse() {
		return classe;
	}

	public void setClasse(String classe) {
		this.classe = classe;
	}

	public String getRace() {
		return race;
	}

	public void setRace(String race) {
		this.race = race;
	}

	public String getPremierMetier() {
		return premierMetier;
	}

	public void setPremierMetier(String premierMetier) {
		this.premierMetier = premierMetier;
	}

	public String getSecondMetier() {
		return secondMetier;
	}

	public void setSecondMetier(String secondMetier) {
		this.secondMetier = secondMetier;
	}

	public Long getLevel() {
		return level;
	}

	public void setLevel(Long level) {
		this.level = level;
	}
}
