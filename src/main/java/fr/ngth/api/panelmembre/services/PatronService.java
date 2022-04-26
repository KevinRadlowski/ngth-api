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
import fr.ngth.api.message.request.PatronForm;
import fr.ngth.api.message.response.ResponseMessage;
import fr.ngth.api.panelmembre.models.Patron;
import fr.ngth.api.panelmembre.repos.PatronRepository;

@Service
public class PatronService {
	@Autowired
	AuthenticationUser authenticationUser;

	@Autowired
	PatronRepository repository;

	@Autowired
	UserRepository userRepository;
	
	public Set<Patron> getMesPatrons(String username) {
		Membre membre = (Membre) userRepository.findByUsername(username).orElseThrow(
				() -> new UsernameNotFoundException("User Not Found with -> username or email : " + username));
		Set<Patron> patrons = membre.getPatrons();
		for (Patron patron : patrons) {
			patron.getMembre().setPassword("");
		}
		return patrons;
	}

	public ResponseEntity<?> postPatron(String username, PatronForm requestPatron) {

		Membre membre = (Membre) userRepository.findByUsername(username).orElseThrow(
				() -> new UsernameNotFoundException("User Not Found with -> username or email : " + username));

		Patron _patron = new Patron(membre, requestPatron.getName(), requestPatron.getUrl(), requestPatron.getQuality(), requestPatron.getNumberJob());

		repository.save(_patron);

		return new ResponseEntity<>(new ResponseMessage("Patron créé!"), HttpStatus.OK);
	}
	
	public ResponseEntity<String> deletePatron(@PathVariable("id") long id) {
		Patron patron = repository.findById(id)
				.orElseThrow(() -> new UsernameNotFoundException("Patron Not Found with -> id : " + id));
		String membre = patron.getMembre().getUsername();
		
		if (authenticationUser.isValidate(membre)) {
			repository.deleteById(id);
			return new ResponseEntity<>("Le patron a été supprimée !", HttpStatus.OK);
		}		
		else {
			return new ResponseEntity<>("Mauvais utilisateur", HttpStatus.FORBIDDEN);
		}	
	}
	
}
