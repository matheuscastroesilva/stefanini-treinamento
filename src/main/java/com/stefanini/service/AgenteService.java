package com.stefanini.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import com.stefanini.model.Agente;
import com.stefanini.repository.AgenteRepository;

@Stateless
public class AgenteService {

	@Inject
	private AgenteRepository agenteRepository;

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void incluir(Agente agente) {
		agenteRepository.incluir(agente);
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<Agente> todos() {
		return this.agenteRepository.todos();
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Agente pegaId(int id) {
		return agenteRepository.buscar(id);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void remove(Agente agente) {
		agente = pegaId(agente.getId());
		if (agente.getId() != null) {
			this.agenteRepository.remover(agente);
		}
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void alterar(Agente agente) {

		this.agenteRepository.alterar(agente);

	}

}
