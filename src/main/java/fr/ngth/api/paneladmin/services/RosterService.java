package fr.ngth.api.paneladmin.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import fr.ngth.api.message.response.ResponseMessage;
import fr.ngth.api.paneladmin.models.Roster;
import fr.ngth.api.paneladmin.repos.RosterMembreRepository;

@Service
public class RosterService {

	@Autowired
	RosterMembreRepository rosterMembreRepository;

	public Roster getRosterMembre(long id) {
		Roster rosterMembre = rosterMembreRepository.findById(id).orElseThrow(
				() -> new UsernameNotFoundException("Tableau de recrutement non trouvé avec -> id : " + id));

		return rosterMembre;
	}

	public List<Roster> getRosterMembreList() {

		List<Roster> rosterMembreList = new ArrayList<>();
		rosterMembreRepository.findAll().forEach(rosterMembreList::add);
		return rosterMembreList;
	}
	
	public ResponseEntity<?> postRosterMembre(Roster rosterMembre) {

		Roster _rosterMembre = new Roster(rosterMembre.getPseudo(), rosterMembre.getGrade(), rosterMembre.getClasse(), rosterMembre.getRole(), rosterMembre.getImg(), rosterMembre.getDeletable());

		rosterMembreRepository.save(_rosterMembre);

		return new ResponseEntity<>(new ResponseMessage("Membre créée!"), HttpStatus.OK);
	}
	
	public ResponseEntity<String> deleteRosterMembre(@PathVariable("id") long id) {
		rosterMembreRepository.deleteById(id);
		return new ResponseEntity<>("Le membre a été supprimée !", HttpStatus.OK);
	}
	
	public ResponseEntity<Roster> updateRosterMembre(@PathVariable("id") long id, @RequestBody Roster rosterMembre) {
		Optional<Roster> rosterMembreData = rosterMembreRepository.findById(id);

		if (rosterMembreData.isPresent()) {
			Roster _rosterMembre = rosterMembreData.get();
			_rosterMembre.setPseudo(rosterMembre.getPseudo());
			_rosterMembre.setRole(rosterMembre.getRole());
			_rosterMembre.setClasse(rosterMembre.getClasse());
			_rosterMembre.setDeletable(rosterMembre.getDeletable());
			_rosterMembre.setGrade(rosterMembre.getGrade());
			_rosterMembre.setImg(rosterMembre.getImg());

			return new ResponseEntity<>(rosterMembreRepository.save(_rosterMembre), HttpStatus.OK);
		} else {

			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
}
