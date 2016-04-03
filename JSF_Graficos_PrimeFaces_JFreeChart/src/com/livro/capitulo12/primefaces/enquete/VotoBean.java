package com.livro.capitulo12.primefaces.enquete;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.primefaces.model.chart.PieChartModel;

public class VotoBean {
	private PieChartModel graficoPizza;
	private List<Voto> votos;

	public VotoBean() {
		this.graficoPizza = new PieChartModel();
		this.votos = new ArrayList<Voto>();
		this.criaGraficoPizza();
	}

	private void criaGraficoPizza() {		
		this.votos.add(new Voto("Marca A", 70));
		this.votos.add(new Voto("Marca B", 20));
		this.votos.add(new Voto("Marca C", 30));
		for (Voto voto : votos) {
			this.graficoPizza.set(voto.getOpcao(), voto.getTotal());
		}
	}

	public PieChartModel getGraficoPizza() {
		this.graficoPizza = new PieChartModel();
		for (Voto voto : votos) {
			voto.setTotal(new Random().nextInt(100));
			this.graficoPizza.set(voto.getOpcao(), voto.getTotal());
		}
		return graficoPizza;
	}

	public void setGraficoPizza(PieChartModel graficoPizza) {
		this.graficoPizza = graficoPizza;
	}

}
