package fr.ngth.api.paneladmin.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.ngth.api.paneladmin.models.Roster;

@Repository
public interface RosterMembreRepository extends CrudRepository<Roster, Long> {

}
