package com.edmar.gerenciador_cursos_api.security.jwt;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.edmar.gerenciador_cursos_api.security.UserPrinciple;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtProvider {

	private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class);

	@Value("${jwt.app.jwtSecret}")
	private String jwtSecret;

	@Value("${jwt.app.jwtExpiration}")
	private int jwtExpiration;

	// Método que gera um token compacto
	public String gerarJwtToken(Authentication authentication) {
		UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();

		return Jwts.builder().setSubject(userPrinciple.getUsername()).setIssuedAt(new Date())
				.setExpiration(new Date(new Date().getTime() + jwtExpiration))
				.signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
	}
	
	//método responsável por validar um token
	public boolean validateJwtToken(String jwt) {

		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(jwt);
			return true;
		} catch (SignatureException e) {
			logger.error("Invalid signature : {} ", e);
		} catch (ExpiredJwtException e) {
			logger.error("Invalid JWT token : {}", e);
		} catch (UnsupportedJwtException e) {
			logger.error("Unsupported JWT token : {}", e);
		} catch (IllegalArgumentException e) {
			logger.error("JWT claims string is empty : {}", e);
		}
		return false;
	}

	public String getUserNameFromJwtToken(String jwt) {
		return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(jwt).getBody().getSubject();
	}
}
