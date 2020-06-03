package fr.ngth.api.paneladmin.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Roster {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "pseudo")
	private String pseudo;
	
	@Column(name = "grade")
	private String grade;
	
	@Column(name = "classe")
	private String classe;
	
	@Column(name = "role")
	private String role;
	
	@Column(name = "img")
	private String img;
	
	@Column(name = "deletable")
	private Long deletable;

	public Roster() {}
	
	public Roster(Long id, String pseudo, String grade, String classe, String role, String img, Long deletable) {
		this.id = id;
		this.pseudo = pseudo;
		this.grade = grade;
		this.classe = classe;
		this.role = role;
		this.img = img;
		this.deletable = deletable;
	}
	
	public Roster(String pseudo, String grade, String classe, String role, String img, Long deletable) {
		this.pseudo = pseudo;
		this.grade = grade;
		this.classe = classe;
		this.role = role;
		this.img = img;
		this.deletable = deletable;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	public String getClasse() {
		return classe;
	}

	public void setClasse(String classe) {
		this.classe = classe;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public Long getDeletable() {
		return deletable;
	}

	public void setDeletable(Long deletable) {
		this.deletable = deletable;
	}

}
