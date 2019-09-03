package com.edmar.gerenciador_cursos_api.inscricao.DTO;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

public class SaveInscricaoDTO {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Long id;
	
	@NotBlank(message="Identificador do participante não pode ser nulo")
	private long id_participante;
	
	@NotBlank(message="Identificador do miniCurso não pode ser nulo")
	private long id_miniCurso;
	

}
