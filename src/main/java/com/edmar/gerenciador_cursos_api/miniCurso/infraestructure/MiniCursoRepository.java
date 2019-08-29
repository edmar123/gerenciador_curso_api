package com.edmar.gerenciador_cursos_api.miniCurso.infraestructure;

import org.springframework.stereotype.Repository;

import com.edmar.gerenciador_cursos_api.infraestructure.GenericRepository;
import com.edmar.gerenciador_cursos_api.miniCurso.MiniCurso;

@Repository
public interface MiniCursoRepository extends GenericRepository<MiniCurso, Long>{

}
