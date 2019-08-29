package com.edmar.gerenciador_cursos_api.professor.infraestructure;

import org.springframework.stereotype.Repository;

import com.edmar.gerenciador_cursos_api.infraestructure.GenericRepository;
import com.edmar.gerenciador_cursos_api.professor.Professor;

@Repository
public interface ProfessorRepository extends GenericRepository<Professor, Long>{

}
