package br.com.model;

import java.util.List;

public class Series {
	private String name;
    private List<Long> data;
 
    public Series() {}
 
    public Series(String name, List<Long> data) {
        this.name = name;
        this.data = data;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the data
	 */
	public List<Long> getData() {
		return data;
	}

	/**
	 * @param data
	 *            the data to set
	 */
	public void setData(List<Long> data) {
		this.data = data;
	}
}
