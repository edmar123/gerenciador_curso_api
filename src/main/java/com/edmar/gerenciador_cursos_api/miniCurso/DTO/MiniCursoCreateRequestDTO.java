package com.edmar.gerenciador_cursos_api.miniCurso.DTO;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.edmar.gerenciador_cursos_api.miniCurso.MiniCurso;

import lombok.Data;
import lombok.NonNull;

@Data
public class MiniCursoCreateRequestDTO {
	
	@Column
	private String nome;
	
	@NotNull(message="A data de realização não pode ser nulo")
	private LocalDate dataRealizacao;

	@NotNull(message="A hora de inicio não pode ser nulo")
	private LocalTime horaInicio;
	
	@NotNull(message="A hora do fim do curso não pode ser nulo")
	private LocalTime horaFim;
	
	@NotNull(message="O total de vagas não pode ser nulo")
	private int totalVaga; 
	
	private String descricao;
	
	@NotBlank(message="O dados de professor não podem ser nulo")
	private ProfessorCreateRequestDTO professor;
	
	public MiniCursoCreateRequestDTO() {
		
	}
	
	public MiniCurso convertToMiniCurso() {
		final MiniCurso miniCurso = new MiniCurso();
		
		miniCurso.setNome(this.nome);
		miniCurso.setDataRealizacao(this.getDataRealizacao());
		miniCurso.setHoraInicio(this.horaInicio);  
		miniCurso.setHoraFim(this.horaFim);  
		miniCurso.setTotalVaga(this.totalVaga); 
		miniCurso.setDescricao(this.descricao); 
		miniCurso.setProfessor(this.professor.convertToProfessor());
		 
		return miniCurso;
	}
}
