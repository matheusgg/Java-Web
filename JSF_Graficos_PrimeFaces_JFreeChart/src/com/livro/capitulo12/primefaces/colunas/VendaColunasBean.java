package com.livro.capitulo12.primefaces.colunas;

import java.util.ArrayList;
import java.util.List;

import org.primefaces.event.ItemSelectEvent;
import org.primefaces.model.chart.CartesianChartModel;
import org.primefaces.model.chart.ChartSeries;

public class VendaColunasBean {
	private CartesianChartModel graficoBarras;
	private boolean showLastChart = false;

	public VendaColunasBean() {
		this.graficoBarras = new CartesianChartModel();
		this.criaGraficoBarras();
	}

	private void criaGraficoBarras() {
		// Dados do Gráfico
		List<VendaColunas> vendas = new ArrayList<VendaColunas>();
		vendas.add(new VendaColunas("2010", 191, 163, 178));
		vendas.add(new VendaColunas("2011", 210, 300, 275));
		vendas.add(new VendaColunas("2012", 35, 45, 60));

		// Séries que utilizarão os dados do gráfico
		ChartSeries brasil = new ChartSeries("Brasil");
		ChartSeries estadosUnidos = new ChartSeries("Estados Unidos");
		ChartSeries alemanha = new ChartSeries("Alemanha");

		for (VendaColunas venda : vendas) {
			brasil.set(venda.getAno(), venda.getVendasBrasil());
			estadosUnidos.set(venda.getAno(), venda.getVendasEstadosUnidos());
			alemanha.set(venda.getAno(), venda.getVendasAlemanha());
		}

		// Adição das séries no modelo do gráfico
		this.graficoBarras.addSeries(brasil);
		this.graficoBarras.addSeries(estadosUnidos);
		this.graficoBarras.addSeries(alemanha);
	}

	public void selecionaSerieGrafico(ItemSelectEvent event) {
		System.out.println(event.getItemIndex());
		System.out.println(event.getSeriesIndex());
		this.showLastChart = true;
	}

	public CartesianChartModel getGraficoBarras() {
		return graficoBarras;
	}

	public void setGraficoBarras(CartesianChartModel graficoBarras) {
		this.graficoBarras = graficoBarras;
	}

	public boolean isShowLastChart() {
		return showLastChart;
	}

	public void setShowLastChart(boolean showLastChart) {
		this.showLastChart = showLastChart;
	}

}
