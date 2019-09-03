package com.edmar.gerenciador_cursos_api.usuario.infraestructure;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.edmar.gerenciador_cursos_api.infraestructure.GenericRepository;
import com.edmar.gerenciador_cursos_api.usuario.Usuario;

@Repository
public interface UsuarioRepository extends GenericRepository<Usuario, Long>{
	
	@Query("SELECT CASE WHEN (COUNT(usuario.id) > 0)  THEN TRUE ELSE FALSE END FROM Usuario usuario WHERE usuario.email = :login AND usuario.email != :loginAIgnorar")
	boolean verificarLoginExistente(@Param("login") final String login, @Param("loginAIgnorar") final String loginAIgnorar);  

}
