package com.livro.capitulo12.jfreechart.colunas;

import java.io.File;
import java.io.FileInputStream;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

public class CidadeColunasBean {
private StreamedContent grafico;
	
	public CidadeColunasBean(){
		this.criaGraficoBarras();
	}

	private void criaGraficoBarras(){
		try{
			JFreeChart graficoBarras = ChartFactory.createBarChart("5 cidades mais populosas de SC", "Cidades", "Popula��o", this.geraDados(), PlotOrientation.VERTICAL, false, true, false);
			File arquivo = new File("barras.png");
			ChartUtilities.saveChartAsPNG(arquivo, graficoBarras, 500, 300);
			this.grafico = new DefaultStreamedContent(new FileInputStream(arquivo), "image/png");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

	private DefaultCategoryDataset geraDados() {
		DefaultCategoryDataset dts = new DefaultCategoryDataset();
		dts.setValue(new Double(299416.0), "Popula��o", "Blumenau");
		dts.setValue(new Double(174187.0), "Popula��o", "Chapec�");
		dts.setValue(new Double(188557.0), "Popula��o", "Crici�ma");
		dts.setValue(new Double(408161.0), "Popula��o", "Florian�polis");
		dts.setValue(new Double(497331.0), "Popula��o", "Joinville");
		return dts;
	}

	public StreamedContent getGrafico() {
		return grafico;
	}

	public void setGrafico(StreamedContent grafico) {
		this.grafico = grafico;
	}

}
