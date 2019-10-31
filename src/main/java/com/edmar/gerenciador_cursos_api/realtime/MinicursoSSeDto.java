package com.edmar.gerenciador_cursos_api.realtime;

import java.time.LocalDate;
import java.time.LocalTime;

import com.edmar.gerenciador_cursos_api.miniCurso.MiniCurso;

import lombok.Data;

@Data
public class MinicursoSSeDto {
	
	private Long id;
	private String nome;
	private LocalDate dataRealizacao;
	private LocalTime horaInicio;
	private int totalVagas;
	private String descricao;
	
	public static MinicursoSSeDto convertToDto(MiniCurso miniCurso) {
		
		MinicursoSSeDto dto = new MinicursoSSeDto();
		dto.id = miniCurso.getId();
		dto.nome = miniCurso.getNome();
		dto.dataRealizacao = miniCurso.getDataRealizacao();
		dto.horaInicio = miniCurso.getHoraInicio();
		dto.totalVagas = miniCurso.getTotalVaga();
		dto.descricao = miniCurso.getDescricao();
		
		return dto;
	}
}
