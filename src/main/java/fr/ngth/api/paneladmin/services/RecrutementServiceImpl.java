package fr.ngth.api.paneladmin.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fr.ngth.api.login.repos.RoleRepository;
import fr.ngth.api.login.services.AuthenticationUser;
import fr.ngth.api.message.response.ResponseMessage;
import fr.ngth.api.paneladmin.models.Recrutement;
import fr.ngth.api.paneladmin.repos.RecrutementRepository;
import fr.ngth.api.paneladmin.repos.RosterMembreRepository;
@Service
public class RecrutementServiceImpl {
	@Autowired
	RecrutementRepository recrutementRepository;
	
	@Autowired
	RosterMembreRepository rosterMembreRepository;

	@Autowired
	AuthenticationUser authService;

	@Autowired
	RoleRepository roleRepository;

	public Recrutement getRecrutementList(long id) {
		Recrutement recrutementList = recrutementRepository.findById(id)
				.orElseThrow(() -> new UsernameNotFoundException("Tableau de recrutement non trouvé avec -> id : " + id));

		return recrutementList;
	}

	public ResponseEntity<?> postRecrutement(Recrutement recrutement) {

		Recrutement _recrutement = new Recrutement(1L, recrutement.getChaman(), recrutement.getChasseur(),
				recrutement.getDemoniste(), recrutement.getDruide(), recrutement.getGuerrier(),
				recrutement.getMage(), recrutement.getPretre(), recrutement.getVoleur());

		recrutementRepository.save(_recrutement);

		return new ResponseEntity<>(new ResponseMessage("Actu créée!"), HttpStatus.OK);
	}

}
