package com.edmar.gerenciador_cursos_api.miniCurso;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.edmar.gerenciador_cursos_api.professor.Professor;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
@Entity
@Table(name="t_mini_curso")
public class MiniCurso implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Long id;
	
	@Column
	private String nome;
	
	@Column(name="data_realizacao")
	@NotBlank(message="A data de realização não pode ser nulo")
	private LocalDate dataRealizacao;
	
	@Column(name="duracao_curso")
	@NotBlank(message="O tempo de duração não pode ser nulo")
	private LocalTime duracaoCurso;
	
	@Column
	@NotBlank(message="O total de vgas não pode ser nulo")
	private Long totalVaga;
	
	@OneToOne()
	@JoinColumn(name="id_professor")
	private Professor professor;
	
}
