package fr.ngth.api.paneladmin.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.ngth.api.paneladmin.models.Recrutement;

@Repository
public interface RecrutementRepository extends CrudRepository<Recrutement, Long> {

}
