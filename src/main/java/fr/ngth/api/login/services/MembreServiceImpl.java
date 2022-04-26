package fr.ngth.api.login.services;

import java.util.ArrayList;
import java.util.Collection;
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

import fr.ngth.api.login.models.Membre;
import fr.ngth.api.login.models.Role;
import fr.ngth.api.login.models.RoleName;
import fr.ngth.api.login.models.User;
import fr.ngth.api.login.repos.MembreRepository;
import fr.ngth.api.login.repos.RoleRepository;
import fr.ngth.api.login.repos.UserRepository;
import fr.ngth.api.message.request.SignUpForm;
import fr.ngth.api.message.response.ResponseMessage;

@Service
public class MembreServiceImpl {

	@Autowired
	MembreRepository membreRepository;

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
		membreRepository.findAll().forEach(membres::add);
		for (Membre membre : membres) {
			membre.setPassword("");
		}
		return membres;
	}

	public Membre findByEmail(String email) {
		return membreRepository.findByEmail(email);
	}

	public ResponseEntity<?> getOneMembre(String username) {
		Membre membre = (Membre) userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException(
						"Utilisateur non trouvé avec le paramètre -> username ou email : " + username));
		membre.setPassword("");

		return new ResponseEntity<>(membre, HttpStatus.OK);

	}

	public Membre getAdminMembre(@PathVariable("id") long id) {
		Membre membre = membreRepository.findById(id).orElseThrow(
				() -> new UsernameNotFoundException("Utilisateur non trouvé avec le paramètre -> id : " + id));
		membre.setPassword("");
		return membre;
	}

	public ResponseEntity<String> deleteMembre(@PathVariable("id") long id) {
		membreRepository.deleteById(id);
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
				encoder.encode(signUpRequest.getPassword()), signUpRequest.getClasse(),
				signUpRequest.getSpecialisation(), signUpRequest.getRace(), signUpRequest.getPremierMetier(),
				signUpRequest.getNiveauPremierMetier(), signUpRequest.getSecondMetier(),
				signUpRequest.getNiveauSecondMetier(), signUpRequest.getLevel());

		Collection<String> strRoles = new HashSet<String>();
		strRoles.add("user");
		Collection<Role> roles = new HashSet<>();

		strRoles.forEach(role -> {
			switch (role) {
			case "user":
				Role membreRole = roleRepository.findByName(RoleName.ROLE_USER).orElseThrow(
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

	public ResponseEntity<?> updateRoleMembre(@PathVariable("id") long id, @RequestBody String roleChange) {
		Optional<User> membreData = userRepository.findById(id);

		if (membreData.isPresent()) {
			Membre _membre = (Membre) membreData.get();
			Collection<Role> roles = new HashSet<>();

			switch (roleChange) {
			case "user":
				Role userRole = roleRepository.findByName(RoleName.ROLE_USER).orElseThrow(
						() -> new RuntimeException("Erreur! -> Cause: Le rôle de l'utilisateur n'a pas été trouvé."));
				roles.add(userRole);

				break;
			case "member":
				Role membreRole = roleRepository.findByName(RoleName.ROLE_MEMBRE).orElseThrow(
						() -> new RuntimeException("Erreur! -> Cause: Le rôle de l'utilisateur n'a pas été trouvé."));
				roles.add(membreRole);

				break;
			case "officer":
				Role officierRole = roleRepository.findByName(RoleName.ROLE_OFFICIER).orElseThrow(
						() -> new RuntimeException("Erreur! -> Cause: Le rôle de l'utilisateur n'a pas été trouvé."));
				roles.add(officierRole);

				break;
			case "gamemaster":
				Role gmRole = roleRepository.findByName(RoleName.ROLE_ADMIN).orElseThrow(
						() -> new RuntimeException("Erreur! -> Cause: Le rôle de l'utilisateur n'a pas été trouvé."));
				roles.add(gmRole);

				break;
			default:
				break;
			}

			_membre.setRoles(roles);
			userRepository.save(_membre);

			return new ResponseEntity<>(HttpStatus.OK);
		} else {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
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
			_membre.setNiveauPremierMetier(updateRequest.getNiveauPremierMetier());
			_membre.setNiveauSecondMetier(updateRequest.getNiveauSecondMetier());
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
	
	public ResponseEntity<?> updateMembreJob(@PathVariable("id") long id, @RequestBody SignUpForm updateRequest) {

		Optional<User> membreData = userRepository.findById(id);

		if (membreData.isPresent()) {
			Membre _membre = (Membre) membreData.get();
			_membre.setPremierMetier(updateRequest.getPremierMetier());
			_membre.setSecondMetier(updateRequest.getSecondMetier());
			_membre.setNiveauPremierMetier(updateRequest.getNiveauPremierMetier());
			_membre.setNiveauSecondMetier(updateRequest.getNiveauSecondMetier());
			userRepository.save(_membre);

			return new ResponseEntity<>(HttpStatus.OK);
		} else {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	public ResponseEntity<?> updateMembreCharacter(@PathVariable("id") long id, @RequestBody SignUpForm updateRequest) {

		Optional<User> membreData = userRepository.findById(id);

		if (membreData.isPresent()) {
			Membre _membre = (Membre) membreData.get();
			_membre.setPseudo(updateRequest.getPseudo());
			_membre.setLevel(updateRequest.getLevel());
			userRepository.save(_membre);

			return new ResponseEntity<>(HttpStatus.OK);
		} else {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
