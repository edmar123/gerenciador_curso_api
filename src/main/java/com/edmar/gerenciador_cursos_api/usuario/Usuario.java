package com.edmar.gerenciador_cursos_api.usuario;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
@Entity
@Table(name="t_usuario")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Long id;
	@Column
	private String nome;
	@Column
	private String email;
	
	@Column
	private String username;
	
	@Column
	private String password;
	
	@ElementCollection(targetClass = Permissao.class)
	@CollectionTable(name = "t_usuario_permissao", joinColumns = @JoinColumn(name = "id_usuario"))
	@Column(name = "permissao")
	@Enumerated(EnumType.STRING)
	private Set<Permissao> permissoes;
		
	public Usuario() {
		
	}

	public Usuario(Long id, String nome, String email, String username, String password, Set<Permissao> permissoes) {
		super();
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.username = username;
		this.password = password;
		this.permissoes = permissoes;
	}
	
	public void inserirPermissoes(Permissao permissao) {
		if (permissao == null) {
			this.permissoes = new HashSet<>();
			return;
		}
		
		this.permissoes.add(permissao);
	}

}
