package beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class UserBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4238318887193546181L;

	/**
	 * Para utilizar omecanismo de injecao do JSF, e preciso que a
	 * propriedadepossua o metodo set, pois e atraves deste metodo que a
	 * instancia e injetada.
	 */
	@ManagedProperty("#{clientBean}")
	private ClientBean clientBean;

	private List<String> lista;

	@PostConstruct
	public void init() {
		System.out.println("UserBean.init()");
		this.lista = new ArrayList<>();
		for (int i = 0; i < 10; i++) {
			this.lista.add("Item " + (i + 1));
		}
	}

	@PreDestroy
	public void shutdown() {
		System.out.println("UserBean.shutdown()");
	}

	/**
	 * @return the clientBean
	 */
	public ClientBean getClientBean() {
		return clientBean;
	}

	/**
	 * @param clientBean
	 *            the clientBean to set
	 */
	public void setClientBean(ClientBean clientBean) {
		this.clientBean = clientBean;
	}

	/**
	 * @return the lista
	 */
	public List<String> getLista() {
		return lista;
	}

	/**
	 * @param lista
	 *            the lista to set
	 */
	public void setLista(List<String> lista) {
		this.lista = lista;
	}

}
