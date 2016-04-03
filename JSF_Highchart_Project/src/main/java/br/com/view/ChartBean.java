package br.com.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.com.model.PieSeries;
import br.com.model.Series;

import com.google.gson.Gson;

@Named
@SessionScoped
public class ChartBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3349897959908280032L;
	private String pieChartData;
	private String seriesChartData;
	private String category;

	/**
	 * Load Pie Chart Data
	 */
	public void loadPieChartData() {
		this.setSeriesChartData(null);
		List<PieSeries> series = new ArrayList<>();

		series.add(new PieSeries("Firefox", 45.0));
		series.add(new PieSeries("IE", 26.8));
		series.add(new PieSeries("Chrome", 12.8));
		series.add(new PieSeries("Opera", 8.5));
		series.add(new PieSeries("Safari", 6.2));
		series.add(new PieSeries("Outros", 0.7));

		this.setPieChartData(new Gson().toJson(series));
	}

	/**
	 * Load Series Chart Data
	 */
	public void loadSeriesChartData() {
		this.setPieChartData(null);
		List<String> categoryList = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			categoryList.add("Série " + (i + 1));
		}

		List<Long> dataSerie1 = new ArrayList<>();
		List<Long> dataSerie2 = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			dataSerie1.add((long) ((i + 1) * 10));
			dataSerie2.add((long) ((i + 1) * 20));
		}

		List<Series> series = new ArrayList<>();

		series.add(new Series("Série 1", dataSerie1));
		series.add(new Series("Série 2", dataSerie2));

		this.setSeriesChartData(new Gson().toJson(series));
		this.setCategory(new Gson().toJson(categoryList));
	}

	/**
	 * @return the pieChartData
	 */
	public String getPieChartData() {
		return pieChartData;
	}

	/**
	 * @param pieChartData
	 *            the pieChartData to set
	 */
	public void setPieChartData(String pieChartData) {
		this.pieChartData = pieChartData;
	}

	/**
	 * @return the seriesChartData
	 */
	public String getSeriesChartData() {
		return seriesChartData;
	}

	/**
	 * @param seriesChartData
	 *            the seriesChartData to set
	 */
	public void setSeriesChartData(String seriesChartData) {
		this.seriesChartData = seriesChartData;
	}

	/**
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * @param category
	 *            the category to set
	 */
	public void setCategory(String category) {
		this.category = category;
	}
}
