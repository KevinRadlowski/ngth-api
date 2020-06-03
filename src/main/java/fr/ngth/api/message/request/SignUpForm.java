package fr.ngth.api.message.request;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class SignUpForm {
	@NotBlank
	private String pseudo;

	@NotBlank
	@Size(min = 3, max = 50)
	private String username;

	@NotBlank
	@Size(max = 60)
	@Email
	private String email;

	private Set<String> role;

	@NotBlank
	@Size(min = 3, max = 40)
	private String password;

	@NotBlank
	private String classe;
	
	@NotBlank
	private String race;
	
	@NotBlank
	private String specialisation;
	
	private String premierMetier;
	
	private Long niveauPremierMetier;
	
	private String secondMetier;
	
	private Long niveauSecondMetier;
	
	private Long level;
	
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

	public String getRace() {
		return race;
	}

	public void setRace(String race) {
		this.race = race;
	}
	
	public String getSpecialisation() {
		return specialisation;
	}

	public void setSpecialisation(String specialisation) {
		this.specialisation = specialisation;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<String> getRole() {
		return this.role;
	}

	public void setRole(Set<String> role) {
		this.role = role;
	}

	public SignUpForm() {

	}
}