package br.com.apirest_springboot.controller;

import static org.springframework.http.ResponseEntity.ok;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.apirest_springboot.repository.UserRepository;
import br.com.apirest_springboot.security.AccountCredentialsVo;
import br.com.apirest_springboot.security.jwt.JWTTokenProvider;
import io.swagger.annotations.ApiOperation;

@Controller
@RequestMapping("/auth")
public class AuthController {
	
	/*
	 * http://localhost:8080/auth/signin { "username":"joao", "password":"admin123"
	 * }
	 */

	@Autowired
	AuthenticationManager manager;

	@Autowired
	JWTTokenProvider provider;

	@Autowired
	UserRepository repository;

	@ApiOperation(value = "Authenticates a user and returns a token")
	@SuppressWarnings("rawtypes")
	@PostMapping(value = "/signin", produces = { "application/json", "application/xml",
			"application/x-yaml" }, consumes = { "application/json", "application/xml", "application/x-yaml" })
	public ResponseEntity signin(@RequestBody AccountCredentialsVo data) {
		try {
			var username = data.getUsername();
			var pasword = data.getPassword();

			manager.authenticate(new UsernamePasswordAuthenticationToken(username, pasword));

			var user = repository.findByUsername(username);

			var token = "";

			if (user != null) {
				token = provider.createToken(username, user.getRoles());
			} else {
				throw new UsernameNotFoundException("Username " + username + " not found!");
			}

			Map<Object, Object> model = new HashMap<>();
			model.put("username", username);
			model.put("token", token);
			return ok(model);
		} catch (AuthenticationException e) {
			throw new BadCredentialsException("Invalid username/password supplied!");
		}
	}

}
