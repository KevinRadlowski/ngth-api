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
import fr.ngth.api.message.request.PatronForm;
import fr.ngth.api.panelmembre.models.Patron;
import fr.ngth.api.panelmembre.services.PatronService;

@RestController
@RequestMapping("/patrons")
public class PatronController {

	@Autowired
	PatronService service;

	@Autowired
	AuthenticationUser authenticationUser;

	@PreAuthorize("#username == authentication.principal.username or hasRole('ROLE_ADMIN')")
	@PostMapping(value = "/creer/{username}")
	public ResponseEntity<?> postPatron(@PathVariable("username") String username, 
			@RequestBody PatronForm requestPatron) {
		return service.postPatron(username, requestPatron);
	}
	
	@GetMapping(value = "/mes-patrons/{username}")
	public Set<Patron> getMesPatrons(@PathVariable("username") String username ) {
		return service.getMesPatrons(username);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deletePatron(@PathVariable("id") long id) {
		return service.deletePatron(id);
	}
	
	
	
}
