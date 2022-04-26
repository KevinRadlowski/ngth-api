package fr.ngth.api.panelmembre.controllers;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.ngth.api.login.services.AuthenticationUser;
import fr.ngth.api.message.request.TalentForm;
import fr.ngth.api.panelmembre.models.Talent;
import fr.ngth.api.panelmembre.services.TalentService;

@RestController
@RequestMapping("/talents")
public class TalentController {
	
	@Autowired
	TalentService service;

	@Autowired
	AuthenticationUser authenticationUser;

	@PreAuthorize("#username == authentication.principal.username or hasRole('ROLE_ADMIN')")
	@PostMapping(value = "/creer/{username}")
	public ResponseEntity<?> postTalent(@PathVariable("username") String username, 
			@RequestBody TalentForm requestTalent) {
		return service.postTalent(username, requestTalent);
	}
	
	@GetMapping(value = "/mes-talents/{username}")
	public Set<Talent> getMesTalents(@PathVariable("username") String username ) {
		return service.getMesTalents(username);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteTalent(@PathVariable("id") long id) {
		return service.deleteTalent(id);
	}
	

}
