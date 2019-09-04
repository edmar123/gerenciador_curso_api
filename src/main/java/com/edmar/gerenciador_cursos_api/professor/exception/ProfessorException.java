package com.edmar.gerenciador_cursos_api.professor.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND)
public class ProfessorException extends RuntimeException {
	
	public ProfessorException(String erro) {
		super(erro);
	}
}
