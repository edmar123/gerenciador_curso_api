package com.edmar.gerenciador_cursos_api.security;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.edmar.gerenciador_cursos_api.usuario.Usuario;
import com.edmar.gerenciador_cursos_api.usuario.infraestructure.UsuarioRepository;

/**
 * O método encontrará um registro da tabela de usuario no banco de dados
 * para criar um UserDetails object para autenticação.
 * @author edmar
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioRepository repository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Usuario user = this.repository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("O usuário não foi encontrado"));

		return UserPrinciple.build(user);
	}

}
