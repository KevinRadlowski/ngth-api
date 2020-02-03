package fr.skunker.api.skunkerapi.login.services;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import fr.skunker.api.skunkerapi.login.message.request.SignUpForm;
import fr.skunker.api.skunkerapi.login.message.response.ResponseMessage;
import fr.skunker.api.skunkerapi.login.models.Membre;
import fr.skunker.api.skunkerapi.login.models.Role;
import fr.skunker.api.skunkerapi.login.models.RoleName;
import fr.skunker.api.skunkerapi.login.models.User;
import fr.skunker.api.skunkerapi.login.repos.MembreRepository;
import fr.skunker.api.skunkerapi.login.repos.RoleRepository;
import fr.skunker.api.skunkerapi.login.repos.UserRepository;

@Service
public class MembreService {

	@Autowired
	MembreRepository repository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	AuthenticationUser authService;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	public List<Membre> getAllMembre() {
		List<Membre> membres = new ArrayList<>();
		repository.findAll().forEach(membres::add);
		for (Membre membre : membres) {
			membre.setPassword("");
		}
		return membres;
	}

	public ResponseEntity<?> getOneMembre(String username) {
		Membre membre = (Membre) userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException(
						"Utilisateur non trouvé avec le paramètre -> username ou email : " + username));
		membre.setPassword("");

		return new ResponseEntity<>(membre, HttpStatus.OK);

	}

	public Membre getAdminMembre(@PathVariable("id") long id) {
		Membre membre = repository.findById(id).orElseThrow(
				() -> new UsernameNotFoundException("Utilisateur non trouvé avec le paramètre -> id : " + id));
		membre.setPassword("");
		return membre;
	}

	public ResponseEntity<String> deleteMembre(@PathVariable("id") long id) {
		repository.deleteById(id);
		return new ResponseEntity<>("L'utilisateur a été supprimé !", HttpStatus.OK);
	}

	public ResponseEntity<?> deleteUser(@PathVariable("username") String username) {

		User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(
				"Utilisateur non trouvé avec le paramètre -> username ou email : " + username));
		userRepository.delete(user);
		return new ResponseEntity<>(new ResponseMessage("L'entreprise a été supprimée !"), HttpStatus.OK);
	}

	public ResponseEntity<?> postMembre(SignUpForm signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return new ResponseEntity<>(new ResponseMessage("Erreur -> Le nom d'utilisateur est déjà utilisé!"),
					HttpStatus.BAD_REQUEST);
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return new ResponseEntity<>(new ResponseMessage("Erreur -> L'adresse email est déjà utilisée!"),
					HttpStatus.BAD_REQUEST);
		}

		// Creating user's account
		Membre user = new Membre(signUpRequest.getPseudo(), signUpRequest.getUsername(), signUpRequest.getEmail(),
				encoder.encode(signUpRequest.getPassword()), signUpRequest.getClasse(), signUpRequest.getRace(),
				signUpRequest.getPremierMetier(), signUpRequest.getSecondMetier(), signUpRequest.getLevel());

		Set<String> strRoles = new HashSet<String>();
		strRoles.add("membre");
		Set<Role> roles = new HashSet<>();

		strRoles.forEach(role -> {
			switch (role) {
			case "membre":
				Role membreRole = roleRepository.findByName(RoleName.ROLE_MEMBRE).orElseThrow(
						() -> new RuntimeException("Erreur! -> Cause: Le rôle de l'utilisateur n'a pas été trouvé."));
				roles.add(membreRole);

				break;
			default:
				break;
			}
		});

		user.setRoles(roles);
		userRepository.save(user);

		return new ResponseEntity<>(new ResponseMessage("L'utilisateur s'est enregistré avec succès!"), HttpStatus.OK);

	}

	public ResponseEntity<?> updateMembre(@PathVariable("id") long id, @RequestBody SignUpForm updateRequest) {

		Optional<User> membreData = userRepository.findById(id);

		if (membreData.isPresent()) {
			Membre _membre = (Membre) membreData.get();
			_membre.setPseudo(updateRequest.getPseudo());
			_membre.setClasse(updateRequest.getClasse());
			_membre.setRace(updateRequest.getRace());
			_membre.setPremierMetier(updateRequest.getPremierMetier());
			_membre.setSecondMetier(updateRequest.getSecondMetier());
			_membre.setLevel(updateRequest.getLevel());
			_membre.setEmail(updateRequest.getEmail());
			_membre.setUsername(updateRequest.getUsername());

			userRepository.save(_membre);

			return new ResponseEntity<>(HttpStatus.OK);
		} else {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	public ResponseEntity<?> updateMembrePassword(@PathVariable("id") long id, @RequestBody SignUpForm updateRequest) {
		Optional<User> membreData = userRepository.findById(id);

		if (membreData.isPresent()) {
			Membre _membre = (Membre) membreData.get();
			_membre.setPassword(encoder.encode(updateRequest.getPassword()));

			userRepository.save(_membre);

			return new ResponseEntity<>(HttpStatus.OK);
		} else {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
