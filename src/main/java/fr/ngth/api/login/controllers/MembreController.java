package fr.ngth.api.login.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.ngth.api.login.models.Membre;
import fr.ngth.api.login.services.AuthenticationUser;
import fr.ngth.api.login.services.MembreServiceImpl;
import fr.ngth.api.message.request.SignUpForm;

@RestController
@RequestMapping("/membre")
public class MembreController {

	@Autowired
	MembreServiceImpl service;

	@Autowired
	AuthenticationUser authenticationUser;

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping
	public List<Membre> getAllMembre() {
		return service.getAllMembre();
	}

	@PreAuthorize("#username == authentication.principal.username or hasRole('ROLE_ADMIN')")
	@GetMapping("/getone/{username}")
	public ResponseEntity<?> getOneMembre(@PathVariable("username") String username) {
		return service.getOneMembre(username);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping(value = "/adminget/{id}")
	public Membre getAdminMembre(@PathVariable("id") long id) {
		return service.getAdminMembre(id);
	}

	@PostMapping(value = "/creer")
	public ResponseEntity<?> postMembre(@Valid @RequestBody SignUpForm signUpRequest) {
		return service.postMembre(signUpRequest);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteMembre(@PathVariable("id") long id) {
		return service.deleteMembre(id);
	}

	@PreAuthorize("#username == authentication.principal.username or hasRole('ROLE_ADMIN')")
	@DeleteMapping("/deleteuser/{username}")
	public ResponseEntity<?> deleteUser(@PathVariable("username") String username) {
		return service.deleteUser(username);
	}

	@PreAuthorize("#id == authentication.principal.id or hasRole('ROLE_ADMIN')")
	@PutMapping("/{id}")
	public ResponseEntity<?> updateMembre(@PathVariable("id") long id, @RequestBody SignUpForm updateRequest) {
		return service.updateMembre(id, updateRequest);
	}

	@PreAuthorize("#id == authentication.principal.id or hasRole('ROLE_ADMIN')")
	@PutMapping("/password/{id}")
	public ResponseEntity<?> updateMembrePassword(@PathVariable("id") long id, @RequestBody SignUpForm updateRequest) {
		return service.updateMembrePassword(id, updateRequest);
	}

}
