package com.edmar.gerenciador_cursos_api.usuario.DTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UsuarioAuthenticationDTO {
	
	@NotBlank
	private String username;

	@NotBlank
	private String password;
	
	public UsuarioAuthenticationDTO() {
		
	}

}
