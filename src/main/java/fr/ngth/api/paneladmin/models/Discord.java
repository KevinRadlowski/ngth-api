package fr.ngth.api.paneladmin.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Discord {

	@Id
	private long id;

	@Column(name = "discord")
	private String discord;
	
	public Discord() {}
	
	public Discord(long id, String discord) {
		this.id = id;
		this.discord = discord;
	}

	public String getDiscord() {
		return discord;
	}

	public void setDiscord(String discord) {
		this.discord = discord;
	}

}
