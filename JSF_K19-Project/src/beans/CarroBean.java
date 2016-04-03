package beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import model.Carro;

@ManagedBean
@SessionScoped
public class CarroBean {
	private List<Carro> carros;
	private Carro carroSelecionado;

	public CarroBean() {
		this.carroSelecionado = new Carro();
		this.carros = new ArrayList<Carro>();
	}

	public void adicionaCarro() {
		this.carros.add(this.carroSelecionado);
		this.carroSelecionado = new Carro();
	}

	/**
	 * @return the carros
	 */
	public List<Carro> getCarros() {
		return carros;
	}

	/**
	 * @param carros
	 *            the carros to set
	 */
	public void setCarros(List<Carro> carros) {
		this.carros = carros;
	}

	/**
	 * @return the carroSelecionado
	 */
	public Carro getCarroSelecionado() {
		return carroSelecionado;
	}

	/**
	 * @param carroSelecionado
	 *            the carroSelecionado to set
	 */
	public void setCarroSelecionado(Carro carroSelecionado) {
		this.carroSelecionado = carroSelecionado;
	}

}
