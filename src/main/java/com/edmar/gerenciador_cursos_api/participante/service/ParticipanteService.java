package com.edmar.gerenciador_cursos_api.participante.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edmar.gerenciador_cursos_api.participante.Participante;
import com.edmar.gerenciador_cursos_api.servico.ServicoGenerico;
import com.edmar.gerenciador_cursos_api.usuario.Permissao;
import com.edmar.gerenciador_cursos_api.usuario.service.UsuarioService;

@Service
public class ParticipanteService  extends ServicoGenerico<Participante, Long>{
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Override
	public void salvar(Participante participante) {
		String loginAntigo = "";
		
		if (participante.getId() != null) {
			loginAntigo = participante.getUsuario().getEmail();
		} 
		
		this.usuarioService.verificarExistenciaLogin(participante.getUsuario().getEmail(), loginAntigo);
		participante.getUsuario().inserirPermissoes(Permissao.ROLE_PARTICIPANTE);
		this.repository.save(participante);
	}
}
