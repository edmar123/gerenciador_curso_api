package com.edmar.gerenciador_cursos_api.security.webConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.edmar.gerenciador_cursos_api.security.UserDetailsServiceImpl;
import com.edmar.gerenciador_cursos_api.security.jwt.JwtAuthEntryPoint;
import com.edmar.gerenciador_cursos_api.security.jwt.JwtAuthTokenFilter;
import com.edmar.gerenciador_cursos_api.usuario.Permissao;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsServiceImpl userDetaisServiceimpl;

	@Autowired
	private JwtAuthEntryPoint unauthorizedHandler;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable()
			.authorizeRequests()
			.antMatchers("/api/usuario/login/**").permitAll()
			.antMatchers("/api/realtime/**").permitAll()
			.anyRequest().authenticated()
		.and()
			.exceptionHandling().authenticationEntryPoint(this.unauthorizedHandler)
		.and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		http.addFilterBefore(authenticationJwtTokenFilter(),UsernamePasswordAuthenticationFilter.class);
	}

	@Bean
	public JwtAuthTokenFilter authenticationJwtTokenFilter() { 
		return new JwtAuthTokenFilter();
	}

	/**
	 * @author edmar
	 * @param AuthenticationManagerBuilder
	 *            SecurityBuilder usado para criar um AuthenticationManager. Permite
	 *            a criação fácil de autenticação de memória, autenticação LDAP,
	 *            autenticação baseada em JDBC, adição de UserDetailsService e
	 *            adição de AuthenticationProvider.
	 */
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetaisServiceimpl).passwordEncoder(this.passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
}
