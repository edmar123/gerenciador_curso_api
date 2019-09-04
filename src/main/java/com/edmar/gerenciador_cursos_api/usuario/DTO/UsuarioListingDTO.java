package com.edmar.gerenciador_cursos_api.usuario.DTO;

import com.edmar.gerenciador_cursos_api.usuario.Usuario;

import lombok.Data;

@Data
public class UsuarioListingDTO {
	
	private Long id;
	
	private String nome;
	
	private String email;
		
	public UsuarioListingDTO(final Usuario usuario) {
		this.id = usuario.getId();
		this.nome = usuario.getNome();
		this.email = usuario.getEmail();
	}
	
}
