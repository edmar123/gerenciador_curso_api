package com.edmar.gerenciador_cursos_api.professor.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edmar.gerenciador_cursos_api.professor.Professor;
import com.edmar.gerenciador_cursos_api.professor.DTO.ProfessorListingDTO;
import com.edmar.gerenciador_cursos_api.professor.service.ProfessorService;

@RestController
@RequestMapping("/api/professor")
public class ProfessorController {
	
	@Autowired
	ProfessorService professorService;
	
	@PostMapping
	@PreAuthorize("hasRole('PROFESSOR')")
	public ResponseEntity<?> salvar(@RequestBody Professor professor){
		this.professorService.salvar(professor);	
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@PutMapping
	@PreAuthorize("hasRole('PROFESSOR')")
	public ResponseEntity<?> atualizar(@RequestBody Professor professor){
		this.professorService.salvar(professor);		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@GetMapping
	@PreAuthorize("hasRole('PROFESSOR')")
	public ResponseEntity<List<ProfessorListingDTO>> listar() { 
		List<Professor> professores = this.professorService.listar();
		List<ProfessorListingDTO> professoresDTO = professores.stream().map(professor -> new ProfessorListingDTO(professor))
				.collect(Collectors.toList());
		return ResponseEntity.ok(professoresDTO);
	}
	@GetMapping("/{id}")
	@PreAuthorize("hasRole('PROFESSOR')")
	public ResponseEntity<Professor> buscarPorId(@PathVariable final long id){
		Optional<Professor> professor = this.professorService.buscarPorId(id);
		return ResponseEntity.ok(professor.get());
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('PROFESSOR')")
	public void remover(@PathVariable final long id) {
		this.professorService.remover(id);
	}
}
