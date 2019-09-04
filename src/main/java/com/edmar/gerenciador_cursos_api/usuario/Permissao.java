package com.edmar.gerenciador_cursos_api.usuario;

public enum Permissao {
	
	ROLE_PROFESSOR("professor"),
	ROLE_PARTICIPANTE("participante");
	
	private String descricao;
	
	private Permissao (String descricao) {
		this.descricao = descricao;
	}
	
	public static Permissao[] getTiposUsuarios() {
		return Permissao.values();
	}

}
