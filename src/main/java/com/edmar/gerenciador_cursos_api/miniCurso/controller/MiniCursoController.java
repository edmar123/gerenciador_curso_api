package com.edmar.gerenciador_cursos_api.miniCurso.controller;

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
import com.edmar.gerenciador_cursos_api.miniCurso.service.MiniCursoService;


@RestController
@RequestMapping("/api/minicurso")
public class MiniCursoController {
	
	@Autowired
	MiniCursoService minicursoService;
	
	@PostMapping
	public ResponseEntity<?> salvar(@RequestBody MiniCurso minicurso){
		this.minicursoService.salvar(minicurso);		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@PutMapping
	public ResponseEntity<?> atualizar(@RequestBody MiniCurso miniCurso){
		this.minicursoService.salvar(miniCurso);		
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}
	
	@GetMapping
	public ResponseEntity<?> listar() { 
		List<MiniCurso> miniCursos = this.minicursoService.listar();
		return ResponseEntity.ok(miniCursos);
	}
	@GetMapping("/{id}")
	public ResponseEntity<?> buscarPorId(@PathVariable final long id){
		Optional<MiniCurso> miniCurso = this.minicursoService.buscarPorId(id);
		return ResponseEntity.ok(miniCurso.get());
	}
	
	@DeleteMapping("/{id}")
	public void remover(@PathVariable final long id) {
		this.minicursoService.remover(id);
	}
	
}
