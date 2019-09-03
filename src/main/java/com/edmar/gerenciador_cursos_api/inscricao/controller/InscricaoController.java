package com.edmar.gerenciador_cursos_api.inscricao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edmar.gerenciador_cursos_api.inscricao.Inscricao;
import com.edmar.gerenciador_cursos_api.inscricao.service.InscricaoService;

@RestController
@RequestMapping("/api/inscricao")
public class InscricaoController {
	
	@Autowired
	private InscricaoService inscricaoService;
	
	@PostMapping()
	public ResponseEntity<?> inscreverNoCurso(@RequestBody Inscricao inscricao){
		this.inscricaoService.inscricaoMiniCurso(inscricao);
		return ResponseEntity.status(HttpStatus.OK).build();
	}
	
}
	

