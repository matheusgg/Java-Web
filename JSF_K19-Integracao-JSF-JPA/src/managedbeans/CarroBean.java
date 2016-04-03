package managedbeans;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import model.Carro;
import model.dao.CarroDAO;

import org.primefaces.component.commandbutton.CommandButton;

@ManagedBean
public class CarroBean {

	private Carro carro;
	private List<Carro> carros;
	private CommandButton btnRemove;

	public CarroBean() {
		this.btnRemove = new CommandButton();
		this.carro = new Carro();
	}

	public void adicionaCarro() {
		CarroDAO dao = new CarroDAO();
		dao.adiciona(this.carro);
		this.carro = new Carro();
		this.carros = null;
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "O carro foi adicionado com sucesso!", "");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	public List<Carro> getCarros() {
		if (this.carros == null) {
			CarroDAO dao = new CarroDAO();
			this.carros = dao.buscaTodos();
		}
		return this.carros;
	}

	public void seleciona(AjaxBehaviorEvent event) {
		for (Carro carro : this.carros) {
			if (carro.isSelected()) {
				this.btnRemove.getAttributes().put("disabled", false);
				break;
			} else {
				this.btnRemove.getAttributes().put("disabled", true);
			}
		}
	}

	public void removeCarros() {
		CarroDAO dao = new CarroDAO();
		for (Carro carro : this.carros) {
			if (carro.isSelected()) {
				dao.remove(carro);
			}
		}
		this.btnRemove.getAttributes().put("disabled", true);
		this.carro = new Carro();
		this.carros = null;
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Os carros foram removidos com sucesso!", "");
		FacesContext.getCurrentInstance().addMessage(null, msg);
	}

	/**
	 * @return the carro
	 */
	public Carro getCarro() {
		return carro;
	}

	/**
	 * @param carro
	 *            the carro to set
	 */
	public void setCarro(Carro carro) {
		this.carro = carro;
	}

	/**
	 * @return the btnRemove
	 */
	public CommandButton getBtnRemove() {
		return btnRemove;
	}

	/**
	 * @param btnRemove
	 *            the btnRemove to set
	 */
	public void setBtnRemove(CommandButton btnRemove) {
		this.btnRemove = btnRemove;
	}

}
