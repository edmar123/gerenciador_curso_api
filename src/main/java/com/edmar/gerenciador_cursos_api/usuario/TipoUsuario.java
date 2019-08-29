package com.edmar.gerenciador_cursos_api.usuario;

public enum TipoUsuario {
	
	PROFESSOR("professor"),
	PARTICIPANTE("participante");
	
	private String descricao;
	
	private TipoUsuario (String descricao) {
		this.descricao = descricao;
	}
	
	public static TipoUsuario[] getTiposUsuarios() {
		return TipoUsuario.values();
	}

}
