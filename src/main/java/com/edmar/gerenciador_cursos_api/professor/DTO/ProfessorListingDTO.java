package com.edmar.gerenciador_cursos_api.professor.DTO;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import com.edmar.gerenciador_cursos_api.professor.Professor;
import com.edmar.gerenciador_cursos_api.usuario.DTO.UsuarioListingDTO;

import lombok.Data;

@Data
public class ProfessorListingDTO {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Long id;
	
	@NotBlank(message="O número da matrícula não pode ser nulo")
	private String matricula;
	
	private List<TelefoneListingDTO> telefones;
	
	private UsuarioListingDTO usuario;

	public ProfessorListingDTO(final Professor professor) {
		this.id = professor.getId();
		this.matricula = professor.getMatricula();
		
		this.telefones = professor.getTelefones().stream()
				.map(fone-> new TelefoneListingDTO(fone))
				.collect(Collectors.toList());
		this.usuario = new UsuarioListingDTO(professor.getUsuario());
	}
}
