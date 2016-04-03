package composite.backingcomponents;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean
@ApplicationScoped
public class ComponentDateBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5613998100610513663L;

	private List<Integer> dias;
	private List<Integer> meses;
	private List<Integer> anos;

	@PostConstruct
	public void init() {
		this.dias = this.loadField(1, 31);
		this.meses = this.loadField(1, 12);
		this.anos = this.loadField(1900, 2100);
	}

	private List<Integer> loadField(int from, int to) {
		List<Integer> list = new ArrayList<>();

		for (int i = from; i <= to; i++) {
			list.add(i);
		}

		return list;
	}

	/**
	 * @return the dias
	 */
	public List<Integer> getDias() {
		return dias;
	}

	/**
	 * @param dias
	 *            the dias to set
	 */
	public void setDias(List<Integer> dias) {
		this.dias = dias;
	}

	/**
	 * @return the meses
	 */
	public List<Integer> getMeses() {
		return meses;
	}

	/**
	 * @param meses
	 *            the meses to set
	 */
	public void setMeses(List<Integer> meses) {
		this.meses = meses;
	}

	/**
	 * @return the anos
	 */
	public List<Integer> getAnos() {
		return anos;
	}

	/**
	 * @param anos
	 *            the anos to set
	 */
	public void setAnos(List<Integer> anos) {
		this.anos = anos;
	}
}
