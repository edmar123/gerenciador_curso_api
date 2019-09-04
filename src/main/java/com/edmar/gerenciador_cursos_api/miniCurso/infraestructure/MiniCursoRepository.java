package com.edmar.gerenciador_cursos_api.miniCurso.infraestructure;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.edmar.gerenciador_cursos_api.infraestructure.GenericRepository;
import com.edmar.gerenciador_cursos_api.miniCurso.MiniCurso;

@Repository
public interface MiniCursoRepository extends GenericRepository<MiniCurso, Long>{
	
	@Query("SELECT CASE WHEN (COUNT(m.id) > 0) THEN TRUE ELSE FALSE END  FROM MiniCurso m WHERE "
			+ " m.horaInicio  BETWEEN :horaInicio AND :horaFim OR"
			+ " m.horaFim BETWEEN :horaInicio AND :horaFim")
	boolean existeMiniCursoEntre(@Param("horaInicio") final LocalTime horaInicio, @Param("horaFim") final LocalTime horaFim);
	
	@Query("SELECT CASE WHEN (COUNT(m.id) > 0) THEN TRUE ELSE FALSE END  FROM MiniCurso m WHERE "
			+ " m.dataRealizacao = :dataRealizacao")
	boolean existeMiniCursoComData(@Param("dataRealizacao") final LocalDate dataRealizacao);
}
