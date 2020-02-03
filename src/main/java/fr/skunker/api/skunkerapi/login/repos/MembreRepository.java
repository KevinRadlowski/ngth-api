package fr.skunker.api.skunkerapi.login.repos;

import org.springframework.data.repository.CrudRepository;

import fr.skunker.api.skunkerapi.login.models.Membre;

public interface MembreRepository extends CrudRepository<Membre, Long> {

}
