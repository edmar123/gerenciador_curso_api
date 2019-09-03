package com.edmar.gerenciador_cursos_api.inscricao;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.edmar.gerenciador_cursos_api.miniCurso.MiniCurso;
import com.edmar.gerenciador_cursos_api.participante.Participante;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name="t_inscricao")
@Data
@EqualsAndHashCode
public class Inscricao implements Serializable{

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Long id;
	
	@OneToOne()
	@JoinColumn(name="id_participante")
	private Participante participante;
	
	@ManyToOne()
	@JoinColumn(name="id_mini_curso")
	private MiniCurso miniCurso;
	
}
