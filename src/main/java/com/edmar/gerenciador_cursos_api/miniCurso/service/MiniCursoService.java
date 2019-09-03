package com.edmar.gerenciador_cursos_api.miniCurso.service;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edmar.gerenciador_cursos_api.miniCurso.MiniCurso;
import com.edmar.gerenciador_cursos_api.miniCurso.exception.MiniCursoException;
import com.edmar.gerenciador_cursos_api.miniCurso.infraestructure.MiniCursoRepository;
import com.edmar.gerenciador_cursos_api.servico.ServicoGenerico;

@Service
public class MiniCursoService  extends ServicoGenerico<MiniCurso, Long>{
	
	@Autowired
	private MiniCursoRepository miniCursoRepository;
	
	@Override
	@Transactional
	public void salvar(final MiniCurso miniCurso) {
		this.existeMiniCurso(miniCurso);
		// Calculando a duração do curso
		miniCurso.calCularDuracaoCurso();
		
		this.repository.save(miniCurso);
	}
	
	/**
	 * Verifica se existe um miniCurso no mesmo horário que o passado como parametro
	 * @param miniCurso 
	 * @return retorna true caso exista um miniCurso entre o periodo de tempo especificado
	 */
	@Transactional
	public boolean existeMiniCurso(final MiniCurso miniCurso) {
		
		LocalDate dataRealizacao = miniCurso.getDataRealizacao();
		LocalTime inicioCurso = miniCurso.getHoraInicio();
		LocalTime fimCurso = miniCurso.getHoraFim();
		
		final boolean existeMiniCurso = this.miniCursoRepository.existeMiniCursoEntre(dataRealizacao, inicioCurso, fimCurso);
		
		if (existeMiniCurso) {
			throw new MiniCursoException("Já existe um mini curso cadastrado neste horário");
		}
		
		return !existeMiniCurso;
		
	}
	
}
