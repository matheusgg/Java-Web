package com.livro.capitulo12.primefaces.pizza;

import java.util.ArrayList;
import java.util.List;

import org.primefaces.model.chart.PieChartModel;

public class VendaBean {
	private PieChartModel graficoPizza;

	public VendaBean() {
		this.graficoPizza = new PieChartModel();
		this.criaGraficoPizza();
	}

	private void criaGraficoPizza() {
		List<Venda> vendaPais = new ArrayList<Venda>();
		vendaPais.add(new Venda("Brasil", 540.50f));
		vendaPais.add(new Venda("Estados Unidos", 500.52f));
		vendaPais.add(new Venda("Inglaterra", 475.30f));
		vendaPais.add(new Venda("França", 400));
		vendaPais.add(new Venda("Alemanha", 397.33f));

		for (Venda venda : vendaPais) {
			this.graficoPizza.set(venda.getPais(), venda.getTotal());
		}
	}

	public PieChartModel getGraficoPizza() {
		return graficoPizza;
	}

	public void setGraficoPizza(PieChartModel graficoPizza) {
		this.graficoPizza = graficoPizza;
	}

}
