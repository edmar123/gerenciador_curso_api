package com.edmar.gerenciador_cursos_api.usuario.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edmar.gerenciador_cursos_api.security.jwt.JwtProvider;
import com.edmar.gerenciador_cursos_api.security.jwt.JwtResponse;
import com.edmar.gerenciador_cursos_api.usuario.DTO.UsuarioAuthenticationDTO;
import com.edmar.gerenciador_cursos_api.usuario.service.UsuarioService;

@CrossOrigin(origins = "*", maxAge = 36000)
@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UsuarioService service;

	@Autowired
	JwtProvider jwtProvider; 

	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody UsuarioAuthenticationDTO loginForm) {

		Authentication authentication = this.authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginForm.getUsername(), loginForm.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwt = this.jwtProvider.gerarJwtToken(authentication);
		
		return ResponseEntity.ok(new JwtResponse(jwt));
	}
}
