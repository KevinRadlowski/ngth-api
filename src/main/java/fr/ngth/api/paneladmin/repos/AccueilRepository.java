package fr.ngth.api.paneladmin.repos;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.ngth.api.paneladmin.models.Discord;

@Repository
public interface AccueilRepository extends CrudRepository<Discord, Long> {

}
