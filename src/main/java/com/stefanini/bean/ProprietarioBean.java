package com.stefanini.bean;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.stefanini.model.Proprietario;
import com.stefanini.model.Sexo;
import com.stefanini.model.Telefone;
import com.stefanini.model.TipoTelefone;
import com.stefanini.service.ProprietarioService;

@Named
@SessionScoped
public class ProprietarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ProprietarioService proprietarioService;
	@Inject
	private Proprietario proprietario;
	@Inject
	private Telefone telefone;

	public String novoProprietario() {
		proprietario = new Proprietario();

		return "cadastro-proprietario?faces-redirect=true";
	}

	public String salvar() {
		proprietarioService.incluir(proprietario);

		return "proprietario-sucesso?faces-redirect=true";
	}

	public void salvarTelefone() {
		if (this.proprietario != null) {
			this.proprietario.adicionarTelefone(telefone);
			this.telefone = new Telefone();
		}
	}

	public Proprietario getProprietario() {
		return proprietario;
	}

	public void setProprietario(Proprietario proprietario) {
		this.proprietario = proprietario;
	}

	public Telefone getTelefone() {
		return telefone;
	}

	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}

	public List<Sexo> getSexos() {
		return Arrays.asList(Sexo.values());
	}

	public List<TipoTelefone> getTipoTelefone() {
		return Arrays.asList(TipoTelefone.values());
	}

}
