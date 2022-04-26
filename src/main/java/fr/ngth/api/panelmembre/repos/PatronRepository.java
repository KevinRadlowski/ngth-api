package fr.ngth.api.panelmembre.repos;

import org.springframework.data.repository.CrudRepository;

import fr.ngth.api.panelmembre.models.Patron;

public interface PatronRepository  extends CrudRepository<Patron, Long> {

}
