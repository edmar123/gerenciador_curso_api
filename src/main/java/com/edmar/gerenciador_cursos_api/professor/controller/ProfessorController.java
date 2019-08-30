package com.edmar.gerenciador_cursos_api.professor.controller;

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

import com.edmar.gerenciador_cursos_api.professor.Professor;
import com.edmar.gerenciador_cursos_api.professor.service.ProfessorService;

@RestController
@RequestMapping("/api/professor")
public class ProfessorController {
	
	@Autowired
	ProfessorService professorService;
	
	@PostMapping
	public ResponseEntity<?> salvar(@RequestBody Professor professor){
		this.professorService.salvar(professor);	
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@PutMapping
	public ResponseEntity<?> atualizar(@RequestBody Professor professor){
		this.professorService.salvar(professor);		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@GetMapping
	public ResponseEntity<?> listar() { 
		List<Professor> professores = this.professorService.listar();
		return ResponseEntity.ok(professores);
	}
	@GetMapping("/{id}")
	public ResponseEntity<?> buscarPorId(@PathVariable final long id){
		Optional<Professor> professor = this.professorService.buscarPorId(id);
		return ResponseEntity.ok(professor.get());
	}
	
	@DeleteMapping("/{id}")
	public void remover(@PathVariable final long id) {
		this.professorService.remover(id);
	}
}
