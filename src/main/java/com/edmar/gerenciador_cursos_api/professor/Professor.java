package com.edmar.gerenciador_cursos_api.professor;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.edmar.gerenciador_cursos_api.usuario.Usuario;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
@Entity
@Table(name="t_professor")
public class Professor implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Long id;
	@Column
	@NotBlank(message="O número da matrícula não pode ser nulo")
	private String matricula;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="id_professor")
	private List<Telefone> telefones;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="id_usuario")
	private Usuario usuario;

	public Professor() {
		
	}
}
