package com.edmar.gerenciador_cursos_api.participante.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edmar.gerenciador_cursos_api.miniCurso.MiniCurso;
import com.edmar.gerenciador_cursos_api.participante.Participante;
import com.edmar.gerenciador_cursos_api.participante.service.ParticipanteService;


@RestController
@RequestMapping("/api/participante")
public class ParticipanteController {
	
	@Autowired
	ParticipanteService participanteService;
	
	@PostMapping
	public ResponseEntity<?> salvar(@RequestBody Participante participante){
		this.participanteService.salvar(participante);	
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@PutMapping
	public ResponseEntity<?> atualizar(@RequestBody Participante participante){
		this.participanteService.salvar(participante);		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@GetMapping
	public ResponseEntity<?> listar() { 
		List<Participante> participantes = this.participanteService.listar();
		return ResponseEntity.ok(participantes);
	}
	@GetMapping("/{id}")
	public ResponseEntity<?> buscarPorId(@PathVariable final long id){
		Optional<Participante> participante = this.participanteService.buscarPorId(id);
		return ResponseEntity.ok(participante.get());
	}
	
	@DeleteMapping("/{id}")
	public void remover(@PathVariable final long id) {
		this.participanteService.remover(id);
	}
}
