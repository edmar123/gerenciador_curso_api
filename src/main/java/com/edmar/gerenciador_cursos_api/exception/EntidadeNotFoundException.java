package com.edmar.gerenciador_cursos_api.exception;

public class EntidadeNotFoundException extends RuntimeException {
	
	public EntidadeNotFoundException(String erro) {
		super(erro);
	}
}
