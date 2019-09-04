package com.edmar.gerenciador_cursos_api.inscricao.DTO;

import com.edmar.gerenciador_cursos_api.miniCurso.MiniCurso;

import lombok.Data;

@Data
public class MiniCursoCreateRequestDTO {
	
	private Long id;
	
	public MiniCurso convertToMiniCurso() {
		final MiniCurso miniCurso = new MiniCurso();
		
		miniCurso.setId(this.getId());
		
		return miniCurso;
	}
}
