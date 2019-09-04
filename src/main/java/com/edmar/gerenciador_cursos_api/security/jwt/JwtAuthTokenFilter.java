package com.edmar.gerenciador_cursos_api.security.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.edmar.gerenciador_cursos_api.security.UserDetailsServiceImpl;

public class JwtAuthTokenFilter extends OncePerRequestFilter {
	
	@Autowired
	private JwtProvider tokenProvider;
	
	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	
	private static final Logger logger = LoggerFactory.getLogger(JwtAuthTokenFilter.class);

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
			
			try {	
				//reculpera o token
				String jwt = getJwt(request);
		
				if (jwt != null && this.tokenProvider.validateJwtToken(jwt) ) {
					
					String username = this.tokenProvider.getUserNameFromJwtToken(jwt);
					
					UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
					
					//Uma implementação de org.springframework.security.core.Authentication
					//projetada para apresentação simples de um nome de usuário e senha.
					UsernamePasswordAuthenticationToken authentication = 
							new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
					
					//Implementação de AuthenticationDetailsSource que constrói o objeto de detalhes a partir de um objeto HttpServletRequest, 
					//criando um WebAuthenticationDetails.
					authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
					
					SecurityContextHolder.getContext().setAuthentication(authentication);
					
				}
				
			} catch (Exception e) {
				  logger.error("Can NOT set user authentication -> Message: {}", e);			
			}
		
			//Faz com que o próximo filtro na cadeia seja chamado ou,
			//se o filtro da chamada for o último filtro na cadeia, 
			//faz com que o recurso no final da cadeia seja invocado.
			filterChain.doFilter(request, response);
	}

	private String getJwt(HttpServletRequest request) {
		String authHeader = request.getHeader("Authorization");
		
		if (authHeader != null && authHeader.startsWith("Bearer ")) {
			return authHeader.replace("Bearer ", "");
		}
		return null;
	}
}
