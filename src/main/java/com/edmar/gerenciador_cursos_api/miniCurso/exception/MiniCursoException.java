package com.edmar.gerenciador_cursos_api.miniCurso.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.BAD_REQUEST)
public class MiniCursoException extends RuntimeException {

	public MiniCursoException(String erro) {
		super(erro);
	}
}
