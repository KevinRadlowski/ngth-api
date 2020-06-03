package fr.ngth.api.login.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.ngth.api.login.jwt.JwtProvider;
import fr.ngth.api.login.repos.RoleRepository;
import fr.ngth.api.login.repos.UserRepository;
import fr.ngth.api.login.services.MembreServiceImpl;
import fr.ngth.api.message.request.LoginForm;
import fr.ngth.api.message.request.SignUpForm;
import fr.ngth.api.message.response.JwtResponse;

@RestController
@RequestMapping("/auth")
public class AuthRestAPIs {

	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	MembreServiceImpl service;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtProvider jwtProvider;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwt = jwtProvider.generateJwtToken(authentication);
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();

		return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities()));
	}
	
	@PostMapping("/signup")
	public ResponseEntity<?> postMembre(@RequestBody SignUpForm signUpRequest) {
		return service.postMembre(signUpRequest);
	}

}