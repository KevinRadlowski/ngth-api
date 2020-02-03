package fr.skunker.api.skunkerapi.login.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.skunker.api.skunkerapi.login.models.Role;
import fr.skunker.api.skunkerapi.login.models.RoleName;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByName(RoleName roleName);
}