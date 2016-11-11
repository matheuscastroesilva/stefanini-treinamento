package com.stefanini.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.chart.PieChartModel;

import com.stefanini.model.Infracao;
import com.stefanini.model.LocalInfracao;
import com.stefanini.service.LocalInfracaoService;

@Named
@RequestScoped
public class LocalInfracaoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private LocalInfracaoService service;
	@Inject
	private LocalInfracao local;

	private PieChartModel graficoPizza;

	@Inject
	private Infracao infracao;

	@PostConstruct
	public void init() {
		createGraficoPizza();

	}

	public void cadastrar() {
		service.incluir(local);
	}

	public PieChartModel getPieModel1() {
		return graficoPizza;
	}

	private void createGraficoPizza() {

		graficoPizza = new PieChartModel();
		
		List<LocalInfracao> localInfracaos;
		localInfracaos = service.lista();
		
		for (LocalInfracao localInfracao : localInfracaos) {
			graficoPizza.set(localInfracao.getDescricaoLocalInfracao(), localInfracao.getVelocidadePermitida());
		}

		graficoPizza.setTitle("Locais com mais Infrações");
		graficoPizza.setLegendPosition("w");
	}

	public LocalInfracao getLocal() {
		return local;
	}

	public void setLocal(LocalInfracao local) {
		this.local = local;
	}

	public Infracao getInfracao() {
		return infracao;
	}

	public void setInfracao(Infracao infracao) {
		this.infracao = infracao;
	}

	public PieChartModel getGraficoPizza() {
		return graficoPizza;
	}

	public void setGraficoPizza(PieChartModel graficoPizza) {
		this.graficoPizza = graficoPizza;
	}

}
