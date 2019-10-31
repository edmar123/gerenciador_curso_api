package com.edmar.gerenciador_cursos_api.miniCurso.controller;

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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edmar.gerenciador_cursos_api.events.RealtimeEvent;
import com.edmar.gerenciador_cursos_api.miniCurso.MiniCurso;
import com.edmar.gerenciador_cursos_api.miniCurso.DTO.MiniCursoCreateRequestDTO;
import com.edmar.gerenciador_cursos_api.miniCurso.service.MiniCursoService;


@RestController
@RequestMapping("/api/minicurso")
public class MiniCursoController {
	
	@Autowired
	MiniCursoService minicursoService;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@PostMapping
	@PreAuthorize("hasRole('PROFESSOR')")
	public ResponseEntity<?> salvar(@RequestBody MiniCursoCreateRequestDTO minicursoDTO){
		final MiniCurso minicurso = minicursoDTO.convertToMiniCurso();
		this.minicursoService.salvar(minicurso);
		this.publisher.publishEvent(new RealtimeEvent(this));
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@PutMapping
	@PreAuthorize("hasRole('PROFESSOR')")
	public ResponseEntity<?> atualizar(@RequestBody MiniCurso miniCurso){
		this.minicursoService.salvar(miniCurso);		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@GetMapping
	@PreAuthorize("hasRole('PROFESSOR') or hasRole('PARTICIPANTE')")
	public ResponseEntity<Optional<List<MiniCurso>>> listar() { 
		List<MiniCurso> miniCursos = this.minicursoService.listar();
		Optional<List<MiniCurso>> minicursosOpt = Optional.ofNullable(miniCursos);
		return ResponseEntity.ok(minicursosOpt);
	}
	@GetMapping("/{id}")
	@PreAuthorize("hasRole('PROFESSOR')")
	public ResponseEntity<MiniCurso> buscarPorId(@PathVariable final long id){
		Optional<MiniCurso> miniCurso = this.minicursoService.buscarPorId(id);
		return miniCurso.isPresent() ? ResponseEntity.ok(miniCurso.get())
				:ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('PROFESSOR')")
	public void remover(@PathVariable final long id) {
		this.minicursoService.remover(id);	
	}
	
}
