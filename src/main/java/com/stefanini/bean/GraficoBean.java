package com.stefanini.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.stefanini.model.Agente;
import com.stefanini.service.AgenteService;

@Named
@SessionScoped
public class GraficoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private AgenteService agenteService;
	@Inject
	private Agente agente;
	private List<Agente> agentes;

	@PostConstruct
	public void init() {
		agente = new Agente();
		agentes = agenteService.todos();

	}
	

	public String atualizar() {
		agenteService.alterar(agente);
		limparFormulario();
		return "agente-atualizado_sucesso?faces-redirect=true";
	}

	public String remover() {
		agenteService.remove(agente);
		limparFormulario();
		return "agente-removido_sucesso?faces-redirect=true";
	}

	public String novoAgente() {
		agente = new Agente();

		return "cadastro-agente?faces-redirect=true";
	}

	public String salva() {
		agenteService.incluir(agente);
		limparFormulario();

		return "agente-sucesso?faces-redirect=true";
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

	public void addMessage(String summary) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public Agente getAgente() {
		return agente;
	}

	public void setAgente(Agente agente) {
		this.agente = agente;
	}

	public void setAgentes(List<Agente> agentes) {
		this.agentes = agentes;
	}

}
