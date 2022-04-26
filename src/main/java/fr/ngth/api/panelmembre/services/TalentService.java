package fr.ngth.api.panelmembre.services;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import fr.ngth.api.login.models.Membre;
import fr.ngth.api.login.repos.UserRepository;
import fr.ngth.api.login.services.AuthenticationUser;
import fr.ngth.api.message.request.TalentForm;
import fr.ngth.api.message.response.ResponseMessage;
import fr.ngth.api.panelmembre.models.Talent;
import fr.ngth.api.panelmembre.repos.TalentRepository;

@Service
public class TalentService {
	@Autowired
	AuthenticationUser authenticationUser;

	@Autowired
	TalentRepository repository;

	@Autowired
	UserRepository userRepository;
	
	public Set<Talent> getMesTalents(String username) {
		Membre membre = (Membre) userRepository.findByUsername(username).orElseThrow(
				() -> new UsernameNotFoundException("User Not Found with -> username or email : " + username));
		Set<Talent> talents = membre.getTalents();
		for (Talent talent : talents) {
			talent.getMembre().setPassword("");
		}
		return talents;
	}

	public ResponseEntity<?> postTalent(String username, TalentForm requestTalent) {

		Membre membre = (Membre) userRepository.findByUsername(username).orElseThrow(
				() -> new UsernameNotFoundException("User Not Found with -> username or email : " + username));

		Talent _talent = new Talent(membre, requestTalent.getName(), requestTalent.getUrl());

		repository.save(_talent);

		return new ResponseEntity<>(new ResponseMessage("Spécialisation créée!"), HttpStatus.OK);
	}
	
	public ResponseEntity<String> deleteTalent(@PathVariable("id") long id) {
		Talent talent = repository.findById(id)
				.orElseThrow(() -> new UsernameNotFoundException("Talent Not Found with -> id : " + id));
		String membre = talent.getMembre().getUsername();
		
		if (authenticationUser.isValidate(membre)) {
			repository.deleteById(id);
			return new ResponseEntity<>("La spécialisation a été supprimée !", HttpStatus.OK);
		}		
		else {
			return new ResponseEntity<>("Mauvais utilisateur", HttpStatus.FORBIDDEN);
		}	
	}
	
}
