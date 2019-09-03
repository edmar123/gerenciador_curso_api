package com.edmar.gerenciador_cursos_api.inscricao.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edmar.gerenciador_cursos_api.inscricao.Inscricao;
import com.edmar.gerenciador_cursos_api.inscricao.exception.InscricaoException;
import com.edmar.gerenciador_cursos_api.inscricao.infraestructure.InscricaoRepository;
import com.edmar.gerenciador_cursos_api.miniCurso.MiniCurso;
import com.edmar.gerenciador_cursos_api.miniCurso.exception.MiniCursoException;
import com.edmar.gerenciador_cursos_api.miniCurso.service.MiniCursoService;
import com.edmar.gerenciador_cursos_api.participante.Participante;
import com.edmar.gerenciador_cursos_api.participante.service.ParticipanteService;
import com.edmar.gerenciador_cursos_api.servico.ServicoGenerico;

@Service
public class InscricaoService extends ServicoGenerico<Inscricao, Long> {

	@Autowired
	private ParticipanteService participanteService;

	@Autowired
	private MiniCursoService minicursoService;
	
	@Autowired
	private InscricaoRepository inscricaoRepository;
	
	@Transactional
	public synchronized void inscricaoMiniCurso(final Inscricao inscricao) {
		Optional<MiniCurso> minicursoFromDB = this.minicursoService.buscarPorId(inscricao.getMiniCurso().getId());

		boolean possuiVaga = minicursoFromDB.get().possuiVaga();
		
		this.existeParticipanteInscrito(inscricao);

		if (!possuiVaga) {
			throw new MiniCursoException("As vagas esgotaram");
		}

		this.repository.save(inscricao);

	}
	
	/**
	 * Método responsável por verificar se o participante que vai se inscrever ja está inscrito no miniCurso
	 * @param idMiniCurso identificador do mini curso
	 * @param idParticipante identificador do paticipante
	 * @return retorna true caso o participante ja esteja inscrito
	 */
	@Transactional
	public boolean existeParticipanteInscrito(final Inscricao inscricao) {
		boolean existeParticipanteInscrito = this.inscricaoRepository.existeParticipante(inscricao.getMiniCurso().getId(), 
				inscricao.getParticipante().getId());
		
		if (existeParticipanteInscrito) {
			throw new InscricaoException("Este Participante já está inscrito");
		}
		return !existeParticipanteInscrito;

	}
}
