package fr.ngth.api.paneladmin.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fr.ngth.api.message.response.ResponseMessage;
import fr.ngth.api.paneladmin.models.Discord;
import fr.ngth.api.paneladmin.repos.AccueilRepository;

@Service
public class AccueilService {
	@Autowired
	AccueilRepository accueilRepository;

	public Discord getDiscordLink(long id) {
		Discord discordLink = accueilRepository.findById(id)
				.orElseThrow(() -> new UsernameNotFoundException("Tableau de recrutement non trouvé avec -> id : " + id));

		return discordLink;
	}

	public ResponseEntity<?> postDiscordLink(Discord discordLink) {
		Discord _discordLink = new Discord(1L, discordLink.getDiscord());

		accueilRepository.save(_discordLink);

		return new ResponseEntity<>(new ResponseMessage("Actu créée!"), HttpStatus.OK);
	}
}
