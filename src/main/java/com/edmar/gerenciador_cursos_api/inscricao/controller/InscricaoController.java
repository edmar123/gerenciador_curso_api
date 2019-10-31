package com.edmar.gerenciador_cursos_api.inscricao.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edmar.gerenciador_cursos_api.events.RealtimeEvent;
import com.edmar.gerenciador_cursos_api.inscricao.Inscricao;
import com.edmar.gerenciador_cursos_api.inscricao.DTO.InscricaoCreateRequestDTO;
import com.edmar.gerenciador_cursos_api.inscricao.service.InscricaoService;

@RestController
@RequestMapping("/api/inscricao")
public class InscricaoController {
	
	@Autowired
	private InscricaoService inscricaoService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@PostMapping()
	@PreAuthorize("hasRole('PARTICIPANTE')")
	public ResponseEntity<?> inscreverNoCurso(@RequestBody InscricaoCreateRequestDTO inscricaoDto){
		
		final Inscricao inscricao = inscricaoDto.convertToInscricao();
		this.inscricaoService.inscricaoMiniCurso(inscricao);
		
		this.publisher.publishEvent(new RealtimeEvent(this));
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
	@GetMapping
	@PreAuthorize("hasRole('PARTICIPANTE')")
	public ResponseEntity<List<Inscricao>> listar() { 
		List<Inscricao> miniCursos = this.inscricaoService.listar();
		return ResponseEntity.ok(miniCursos); 
	}
	@GetMapping("/{id}")
	@PreAuthorize("hasRole('PROFESSOR')")
	public ResponseEntity<Inscricao> buscarPorId(@PathVariable final long id){
		Optional<Inscricao> miniCurso = this.inscricaoService.buscarPorId(id);
		return miniCurso.isPresent() ? ResponseEntity.ok(miniCurso.get())
				:ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('PROFESSOR')")
	public void remover(@PathVariable final long id) {
		this.inscricaoService.remover(id);	
	}
}
	

