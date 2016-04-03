package com.livro.capitulo12.jfreechart.pizza;

import java.io.File;
import java.io.FileInputStream;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

public class CidadeBean {
	private StreamedContent grafico;
	
	public CidadeBean(){
		this.criaGraficoPizza();
	}

	private void criaGraficoPizza(){
		try{
			JFreeChart graficoPizza = ChartFactory.createPieChart("5 cidades mais populosas de SC", this.geraDados(), true, true, false);
			File arquivo = new File("pizza.png");
			ChartUtilities.saveChartAsPNG(arquivo, graficoPizza, 500, 300);
			this.grafico = new DefaultStreamedContent(new FileInputStream(arquivo), "image/png");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	private DefaultPieDataset geraDados() {
		DefaultPieDataset dts = new DefaultPieDataset();
		dts.setValue("Joinville", new Double(497331.0));
		dts.setValue("Blumenau", new Double(299416.0));
		dts.setValue("Chapec�", new Double(174187.0));
		dts.setValue("Crici�ma", new Double(188557.0));
		dts.setValue("Frorian�polis", new Double(408161.0));
		return dts;
	}

	public StreamedContent getGrafico() {
		return grafico;
	}

	public void setGrafico(StreamedContent grafico) {
		this.grafico = grafico;
	}
	
}
