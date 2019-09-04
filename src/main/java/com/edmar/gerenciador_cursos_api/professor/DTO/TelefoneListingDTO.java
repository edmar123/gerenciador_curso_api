package com.edmar.gerenciador_cursos_api.professor.DTO;

import com.edmar.gerenciador_cursos_api.professor.Telefone;

import lombok.Data;

@Data
public class TelefoneListingDTO {

	private Long id;
	
	private String numero;
	
	public TelefoneListingDTO( final Telefone telefone) {
		this.id = telefone.getId();
		this.numero = telefone.getNumero();
	}
}
