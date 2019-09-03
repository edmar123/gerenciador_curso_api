package com.edmar.gerenciador_cursos_api.usuario.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edmar.gerenciador_cursos_api.servico.ServicoGenerico;
import com.edmar.gerenciador_cursos_api.usuario.Usuario;
import com.edmar.gerenciador_cursos_api.usuario.exception.UsuarioException;
import com.edmar.gerenciador_cursos_api.usuario.infraestructure.UsuarioRepository;

@Service
public class UsuarioService  extends ServicoGenerico<Usuario, Long>{
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	/**
	 * Método responsável por verificar se existe um login já cadastrado.
	 * Caso seja uma edição e o login existente seja o mesmo que o passado como parâmetro,
	 * será ignorado na validação
	 * @param login novo login a ser inserido
	 * @param loginAIgnorar login a ser ignorado caso seja edição
	 * @return
	 */
	public boolean verificarExistenciaLogin(final String login, final String loginAIgnorar) {
		boolean existeLogin = this.usuarioRepository.verificarLoginExistente(login, loginAIgnorar);
		
		if (existeLogin) {
			throw new UsuarioException("Este login já existe no sistema!");
		}
		
		return existeLogin;
	}
}
