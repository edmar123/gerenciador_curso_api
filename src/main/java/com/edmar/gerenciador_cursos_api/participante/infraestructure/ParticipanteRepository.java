package com.edmar.gerenciador_cursos_api.participante.infraestructure;

import org.springframework.stereotype.Repository;

import com.edmar.gerenciador_cursos_api.infraestructure.GenericRepository;
import com.edmar.gerenciador_cursos_api.participante.Participante;

@Repository
public interface ParticipanteRepository extends GenericRepository<Participante, Long> {

}
