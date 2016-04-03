package br.com.cdi.beans;

import java.io.Serializable;
import java.util.Random;

import javax.enterprise.event.Event;
import javax.enterprise.inject.New;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import lombok.Getter;
import br.com.cdi.alternative.GenericProdutoDAO;
import br.com.cdi.dao.ClienteDAO;
import br.com.cdi.model.Cliente;
import br.com.cdi.qualificadores.Remetente;

/**
 * Com o CDI 1.1 não é mais necessária a criação do arquivo beans.xml.
 * Apesentacao: <http://www.infoq.com/br/presentations/muito-alem-de-injecao-de-
 * dependencias?utm_source=infoq&utm_medium=videos_homepage&utm_campaign=
 * videos_row1>
 * 
 * @author Matheus
 *
 */
@Named
@ViewScoped
public class AppBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2722468376639093659L;

	@Inject
	@New(ClienteDAO.class)
	private ClienteDAO clienteDAO;

	@Getter
	private Cliente cliente;

	@Inject
	@Remetente
	private String remetente;

	@Inject
	private GenericProdutoDAO genericProdutoDAO;

	@Inject
	private HttpServletRequest request;

	@Inject
	private Event<HttpServletRequest> requestEvent;

	public void testObserver() {
		this.requestEvent.fire(this.request);
		this.showMsg(this.requestEvent.toString());
	}

	public void testDecorator() {
		this.showMsg(this.request.getAttribute(null).toString());
	}

	public void testAlternative() {
		this.showMsg(this.genericProdutoDAO.save());
	}

	public void testRemetente() {
		this.showMsg(this.remetente);
	}

	public void list() {
		this.cliente = this.clienteDAO.findCliente(1l);
	}

	public void save() {
		Cliente cliente = new Cliente();
		cliente.setNome("Cliente " + new Random().nextInt(100));
		this.clienteDAO.save(cliente);
		this.showMsg("Cliente salvo com sucesso!");
	}

	public void showFrendlyMessage() {
		this.showMsg("CDI 1.1... O arquivo beans.xml não é mais necessário!");
	}

	public void showMsg(String msg) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(msg));
	}

}
