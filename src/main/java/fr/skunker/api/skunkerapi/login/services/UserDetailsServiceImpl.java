package fr.skunker.api.skunkerapi.login.services;

import fr.skunker.api.skunkerapi.login.models.User;
import fr.skunker.api.skunkerapi.login.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserRepository userRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(
				"Utilisateur non trouvé avec le paramètre -> username ou email : " + username));

		return UserPrinciple.build(user);
	}
}