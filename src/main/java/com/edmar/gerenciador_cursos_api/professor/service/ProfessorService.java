package com.edmar.gerenciador_cursos_api.professor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edmar.gerenciador_cursos_api.professor.Professor;
import com.edmar.gerenciador_cursos_api.servico.ServicoGenerico;
import com.edmar.gerenciador_cursos_api.usuario.service.UsuarioService;

@Service
public class ProfessorService  extends ServicoGenerico<Professor, Long>{
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Override
	public void salvar(Professor professor) {
		String loginAntigo = "";
		
		if (professor.getId() != null) {
			loginAntigo = professor.getUsuario().getEmail();
		} 
		
		this.usuarioService.verificarExistenciaLogin(professor.getUsuario().getEmail(), loginAntigo);
		
		this.repository.save(professor);
	}
	
}
