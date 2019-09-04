package com.edmar.gerenciador_cursos_api.security.jwt;
//é retornado pelo servidor após a autenticação bem sucedida
public class JwtResponse {
	
	private String token;
	
	private String type = "Bearer ";
	
	public JwtResponse(String accessToken) {
		this.token = accessToken;
	}
	
	public String getAccessToken() {
		return this.token;
	}
	
	public void setAccessToken(String accessToken) {
		this.token = accessToken;
	}
	
	public String getTokenType() {
		return type;
	}
	
	public void setTokenType(String tokenType) {
		this.type = tokenType;
	}
}
