package com.edmar.gerenciador_cursos_api.inscricao.infraestructure;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.edmar.gerenciador_cursos_api.infraestructure.GenericRepository;
import com.edmar.gerenciador_cursos_api.inscricao.Inscricao;

@Repository
public interface InscricaoRepository extends GenericRepository<Inscricao, Long> {
	
	/**
	 * Método responsável por verificar se o participante que vai se inscrever ja está inscrito no miniCurso
	 * @param idMiniCurso identificador do mini curso
	 * @param idParticipante identificador do paticipante
	 * @return retorna true caso o participante ja esteja inscrito
	 */
	@Query("SELECT CASE WHEN (COUNT(insc.id) > 0)  THEN TRUE ELSE FALSE END FROM Inscricao insc"
			+ " WHERE insc.miniCurso.id = :idMiniCurso AND insc.participante.id = :idParticipante")
	boolean existeParticipante(@Param("idMiniCurso") final long idMiniCurso, @Param("idParticipante") final long idParticipante); 
}
