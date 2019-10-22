package com.edmar.gerenciador_cursos_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableAsync
@EnableScheduling
@SpringBootApplication
@EnableAspectJAutoProxy
public class GerenciadorCursosApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(GerenciadorCursosApiApplication.class, args);
		
		System.out.println(new BCryptPasswordEncoder().encode("123123"));
	}

}
