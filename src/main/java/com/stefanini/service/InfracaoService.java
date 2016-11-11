package com.stefanini.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import com.stefanini.model.Agente;
import com.stefanini.model.Infracao;
import com.stefanini.repository.InfracaoRepository;

@Stateless
public class InfracaoService {

	@Inject
	private InfracaoRepository infracaoRepositorio;

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void incluir(Infracao i) {
		infracaoRepositorio.incluir(i);
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<Infracao> todos() {
		return this.infracaoRepositorio.todos();
	}

	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public Infracao pegaId(Long id) {
		return infracaoRepositorio.busca(id);
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void remove(Infracao infracao) {
		infracao = pegaId(infracao.getId());
		if (infracao.getId() != null) {
			this.infracaoRepositorio.remove(infracao);
		}
	}

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void alterar(Infracao infracao) {

		this.infracaoRepositorio.altera(infracao);

	}

}
