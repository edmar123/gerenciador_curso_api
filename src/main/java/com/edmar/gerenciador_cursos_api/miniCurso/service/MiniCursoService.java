package com.edmar.gerenciador_cursos_api.miniCurso.service;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
		// Garantindo que todas as threads fiquem sincronizadas, para que nao haja o risco 
		// de cadastrar duas instancias de curso no mesmo horário e data
		synchronized (MiniCursoService.class) {
			
			this.existeMiniCurso(miniCurso);
			// Calculando a duração do curso
			miniCurso.calCularDuracaoCurso();
			
			this.repository.save(miniCurso);
		}
	}
	
	@Transactional
	public Long getTotalVagas() {
		return this.miniCursoRepository.getTotalDeVagas();
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
		
		boolean existeMesmoHorario = this.miniCursoRepository.existeMiniCursoEntre(inicioCurso, fimCurso);
		boolean existeComMesmaData = this.miniCursoRepository.existeMiniCursoComData(dataRealizacao);
		
		if (existeComMesmaData) {
			if (existeMesmoHorario) {
				throw new MiniCursoException("Já existe um mini curso cadastrado neste horário");
			}
			
		}
		
		return false;
		
	}
	
}
