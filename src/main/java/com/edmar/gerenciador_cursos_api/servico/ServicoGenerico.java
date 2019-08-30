package com.edmar.gerenciador_cursos_api.servico;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.edmar.gerenciador_cursos_api.infraestructure.GenericRepository;

public abstract class ServicoGenerico<T,ID> {
	
	@Autowired
	private GenericRepository<T, ID> repository;
	
	@Transactional
	public void salvar(final T entidade) {
		this.repository.save(entidade);
	}
	
	@Transactional(readOnly=true)
	public void remover(final ID id) {
		this.repository.deleteById(id);
	}
	
	@Transactional(readOnly=true)
	public Optional<T> buscarPorId(final ID id) {
		return this.repository.findById(id);
	}
	
	@Transactional(readOnly=true)
	public List<T> listar(){
		return this.repository.findAll();
	}
}
