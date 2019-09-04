package com.edmar.gerenciador_cursos_api.inscricao.DTO;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.edmar.gerenciador_cursos_api.inscricao.Inscricao;

import lombok.Data;

@Data
public class InscricaoCreateRequestDTO {
	
	@NotNull(message="A data de inscrição não pode ser nulo")
	private LocalDate dataInscricao;
		
	@NotBlank(message="Identificador do participante não pode ser nulo")
	private ParticipanteCreateRequestDTO participante;
	
	@NotBlank(message="Identificador do miniCurso não pode ser nulo")
	private MiniCursoCreateRequestDTO miniCurso;
	
	public Inscricao convertToInscricao() {
		final Inscricao inscricao = new Inscricao();
		
		inscricao.setDataInscricao(this.getDataInscricao());
		inscricao.setParticipante(this.participante.convertToParticpante());
		inscricao.setMiniCurso(this.miniCurso.convertToMiniCurso());
		
		return inscricao;
	}
}
