package com.edmar.gerenciador_cursos_api.miniCurso;

import java.io.Serializable;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Period;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.edmar.gerenciador_cursos_api.participante.Participante;
import com.edmar.gerenciador_cursos_api.professor.Professor;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

@Data
@EqualsAndHashCode
@Entity
@Table(name="t_mini_curso")
public class MiniCurso implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Long id;
	
	@Column
	private String nome;
	
	@Column(name="data_realizacao")
	private LocalDate dataRealizacao;
	
	@Column(name="duracao_curso")
	private LocalTime duracaoCurso;
	
	@Column(name="hora_inicio")
	private LocalTime horaInicio;
	
	@Column(name="hora_fim")
	private LocalTime horaFim;
	@Column
	private int totalVaga; 
	
	@OneToOne()
	@JoinColumn(name="id_professor")
	private Professor professor;

	public MiniCurso() { 
		
	}
	/**
	 * A duraçao do curso é calculado de acordo com a hora de inicio do curso e a hora do termino
	 * @author edmar
	 */
	public void calCularDuracaoCurso() {
		Duration diferenca = Duration.between(horaInicio, horaFim);
		this.duracaoCurso = LocalTime.ofNanoOfDay(diferenca.toNanos());
		System.out.println("Calculando a duração do miniCurso" + this.duracaoCurso);

	}
	
	public boolean possuiVaga() {
		if (this.totalVaga > 0) {
			return true;
		}
		return false;
	}
	
	public void atualizarNumeroDevagas() {
		
		if (this.totalVaga == 0) {
			return;
		}
		int totalAtualizado = this.totalVaga -1;
		
		this.setTotalVaga(totalAtualizado);
	}
	
}
