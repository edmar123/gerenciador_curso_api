package com.edmar.gerenciador_cursos_api.usuario.DTO;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class UsuarioAuthenticationDTO {
	
	@NotBlank
	@Size(min = 3, max = 60)
	private String username;

	@NotBlank
	@Size(min = 6, max = 40)
	private String password;
	
	public UsuarioAuthenticationDTO() {
		
	}

}
