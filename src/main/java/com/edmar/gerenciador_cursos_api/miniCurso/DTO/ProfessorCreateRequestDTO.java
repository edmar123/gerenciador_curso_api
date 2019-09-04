package com.edmar.gerenciador_cursos_api.miniCurso.DTO;

import com.edmar.gerenciador_cursos_api.professor.Professor;

import lombok.Data;

@Data
public class ProfessorCreateRequestDTO {
	
	private Long id;
	
	public Professor convertToProfessor() {
		
		final Professor professor = new Professor();
		professor.setId(this.getId());
		
		return professor;
	}

}
