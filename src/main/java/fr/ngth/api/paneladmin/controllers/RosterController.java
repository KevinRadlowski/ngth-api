package fr.ngth.api.paneladmin.controllers;

import java.util.List;

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

import fr.ngth.api.paneladmin.models.Roster;
import fr.ngth.api.paneladmin.services.RosterService;

@RestController
@RequestMapping("/roster")
public class RosterController {
	
	@Autowired
	RosterService service;
	
	@GetMapping(value = "/{id}")
	public Roster getRosterMembre(@PathVariable("id") long id) {
		return service.getRosterMembre(id);
	}
	
	@GetMapping("/roster-membre-list")
	public List<Roster> getRosterMemberList() {
		return service.getRosterMembreList();
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping(value = "/post-roster-member")
	public ResponseEntity<?> postRosterMembre(@RequestBody Roster rosterMembre) {
		return service.postRosterMembre(rosterMembre);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteRosterMembre(@PathVariable("id") long id) {
		return service.deleteRosterMembre(id);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PutMapping("/{id}")
	public ResponseEntity<Roster> updateRosterMembre(@PathVariable("id") long id, @RequestBody Roster rosterMembre) {
		return service.updateRosterMembre(id, rosterMembre);
	}

}
