package com.stefanini.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.stefanini.model.Agente;
import com.stefanini.service.AgenteService;

@Named
@SessionScoped
public class AgenteBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private AgenteService agenteService;
	@Inject
	private Agente agente;
	private List<Agente> agentes;

	@PostConstruct
	public void init() {
		agentes = agenteService.todos();
	}

	public String novoAgente() {
		agente = new Agente();

		return "cadastro-agente";
	}

	public void salva() {
		agenteService.incluir(agente);
		limparFormulario();
	}

	private void limparFormulario() {
		agente = new Agente();
		agentes = null;
	}

	public List<Agente> getAgentes() {
		if (agentes == null) {
			agentes = agenteService.todos();
		}
		return agentes;
	}

	public Agente getAgente() {
		return agente;
	}

	public void setAgente(Agente agente) {
		this.agente = agente;
	}

}
