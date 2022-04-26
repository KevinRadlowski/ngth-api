package fr.ngth.api.paneladmin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.ngth.api.login.services.AuthenticationUser;
import fr.ngth.api.paneladmin.models.Discord;
import fr.ngth.api.paneladmin.services.AccueilService;

@RestController
@RequestMapping("/accueil")
public class AccueilController {
	
	@Autowired
	AccueilService service;

	@Autowired
	AuthenticationUser authenticationUser;

	@GetMapping(value = "/get-discord-link/{id}")
	public Discord getDiscordLink(@PathVariable("id") long id) {
		return service.getDiscordLink(id);
	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PutMapping(value = "/update-discord-link")
	public ResponseEntity<?> postDiscordLink(@RequestBody Discord discord) {
		return service.postDiscordLink(discord);
	}

}
