package com.edmar.gerenciador_cursos_api.inscricao.DTO;

import com.edmar.gerenciador_cursos_api.participante.Participante;

import lombok.Data;

@Data
public class ParticipanteCreateRequestDTO {
	
	private Long id;
	
	public Participante convertToParticpante() {
		final Participante participante = new Participante();
		
		participante.setId(this.getId());
		
		return participante;
	}
}
