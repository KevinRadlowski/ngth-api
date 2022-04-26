package fr.ngth.api.login.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import fr.ngth.api.panelmembre.models.Patron;
import fr.ngth.api.panelmembre.models.Talent;

@Entity
public class Membre extends User {

	@Column(name = "pseudo")
	private String pseudo;

	@Column(name = "classe")
	private String classe;

	@Column(name = "race")
	private String race;
	
	@Column(name = "specialisation")
	private String specialisation;

	@Column(name = "premierMetier")
	private String premierMetier;
	
	@Column(name= "niveauPremierMetier")
	private Long niveauPremierMetier;

	@Column(name = "secondMetier")
	private String secondMetier;

	@Column(name= "niveauSecondMetier")
	private Long niveauSecondMetier;

	@Column(name = "level")
	private Long level;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "membre")
	@JsonIgnore
	private Set<Patron> patrons;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "membre")
	@JsonIgnore
	private Set<Talent> talents;
	
	public Membre() {
	}

	public Membre(String pseudo, String username, String email, String password, String classe, String specialisation, String race,
			String premierMetier, Long niveauPremierMetier, String secondMetier, Long niveauSecondMetier, Long level) {
		super(username, email, password);
		this.pseudo = username;
		this.race = race;
		this.classe = classe;
		this.specialisation = specialisation;
		this.premierMetier = premierMetier;
		this.niveauPremierMetier = niveauPremierMetier;
		this.secondMetier = secondMetier;
		this.niveauSecondMetier = niveauSecondMetier;
		this.level = level;
		this.patrons = new HashSet<Patron>();
		this.talents = new HashSet<Talent>();
		
	}

	public Membre(String pseudo, Long id, String username, String email, String password, String classe, String specialisation, String race,
			String premierMetier, Long niveauPremierMetier, String secondMetier, Long niveauSecondMetier, Long level) {
		super(id, username, email, password);
		this.pseudo = username;
		this.race = race;
		this.classe = classe;
		this.specialisation = specialisation;
		this.premierMetier = premierMetier;
		this.niveauPremierMetier = niveauPremierMetier;
		this.secondMetier = secondMetier;
		this.niveauSecondMetier = niveauSecondMetier;
		this.level = level;
		this.patrons = new HashSet<Patron>();
		this.talents = new HashSet<Talent>();
	}

	public Long getNiveauPremierMetier() {
		return niveauPremierMetier;
	}

	public void setNiveauPremierMetier(Long niveauPremierMetier) {
		this.niveauPremierMetier = niveauPremierMetier;
	}

	public Long getNiveauSecondMetier() {
		return niveauSecondMetier;
	}

	public void setNiveauSecondMetier(Long niveauSecondMetier) {
		this.niveauSecondMetier = niveauSecondMetier;
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
	
	public String getSpecialisation() {
		return specialisation;
	}

	public void setSpecialisation(String specialisation) {
		this.specialisation = specialisation;
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
	
	public Set<Patron> getPatrons() {
		return patrons;
	}

	public void setPatrons(Set<Patron> patrons) {
		this.patrons = patrons;
	}

	public void setPatron(Patron patron) {
		this.patrons.add(patron);
	}
	
	public Set<Talent> getTalents() {
		return talents;
	}

	public void setTalents(Set<Talent> talents) {
		this.talents = talents;
	}
}

