package fr.ngth.api.login.dto;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

//import javax.validation.constraints.Email;
//import javax.validation.constraints.NotEmpty;


public class PasswordForgotDto {

	@Email
	@NotEmpty
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
