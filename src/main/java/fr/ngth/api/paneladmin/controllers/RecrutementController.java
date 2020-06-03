package fr.ngth.api.paneladmin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.ngth.api.login.services.AuthenticationUser;
import fr.ngth.api.paneladmin.models.Recrutement;
import fr.ngth.api.paneladmin.services.RecrutementServiceImpl;

@RestController
@RequestMapping("/recrutement")
public class RecrutementController {

	@Autowired
	RecrutementServiceImpl service;

	@Autowired
	AuthenticationUser authenticationUser;

	@GetMapping(value = "/get-recrutement/{id}")
	public Recrutement getRecrutementList(@PathVariable("id") long id) {
		return service.getRecrutementList(id);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PutMapping(value = "/update-recrutement")
	public ResponseEntity<?> postRecrutement(@RequestBody Recrutement recrutement) {
		return service.postRecrutement(recrutement);
	}

}
