package br.com.cdi;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.New;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.cdi.model.Usuario;
import br.com.cdi.model.dao.UsuarioDAO;
import br.com.cdi.model.dao.UsuarioDAOImpl3;

/**
 * Esta anota��o permite que este bean esteja dispon�vel para utiliza��o nas
 * EL's do jsf. Para utiliza��o do CDI, � necess�rio a cria��o do arquivo
 * beans.xml. Este arquivo � respons�vel por informar o container que esta
 * aplica��o possui beans CDI. Desta forma, o jsf conseguir� resolver esta
 * inst�ncia mesmo sem a anota��o '@ManagedBean'. A anota��o '@SessionScoped' do
 * pacote javax.enterprise.context foi utilizada pois foi projetada para
 * utiliza��o junto com '@Named' e CDI, por�m para sua utiliza��o � preciso
 * implementar a interface Serializable na classe e em todos os objetos que
 * comp�es esta classe. Al�m disso, � necess�rio que este ManagedBean possua um
 * construtor padr�o.
 * 
 * @author Matheus
 * 
 */
@Named
@SessionScoped
public class Bean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	{
		System.out.println(Bean.serialVersionUID);
	}

	private final Usuario usuario;

	private Usuario administrador;

	/**
	 * � poss�vel utilizar inje��o de depend�ncia nos cnstrutores. Desta forma,
	 * uma implementa��o de Usu�rio ser� procurada para ser injetada neste ponto
	 * de inje��o.
	 * 
	 * @param usuario
	 */
	@Inject
	public Bean(Usuario usuario) {
		this.usuario = usuario;
	}

	public Bean() {
		this.usuario = new Usuario();
	}

	/**
	 * '@Inject' � respons�vel por injetar um componente CDI. Com ela, n�o �
	 * necess�rio informar outros par�metros, pois o pr�prio CDI se encarrega
	 * encontrar uma implementa��o para esta interface. '@New' indica qual
	 * implementa��o deve ser utilizada para inje��o. Isto � necess�rio quando
	 * existe mais de uma implementa��o, deste modo, o CDI n�o consegue
	 * determinar qual implementa��o ele deve utilizar para inje��o. Com '@New',
	 * dizemos ao CDI que ignore as outras implementa��es e utilize
	 * UsuarioDAOImpl3. J� a anota��o'@Qualificador' indica que a implementa��o
	 * que estiver anotada com '@Qualificador' ser� utilizada para inje��o.
	 */
	@Inject
	@New(UsuarioDAOImpl3.class)
	private UsuarioDAO usuarioDAO;

	public void execute() {
		this.usuarioDAO.salva(null);
	}

	/**
	 * @return the frase
	 */
	public String getFrase() {
		return "Usando CDI";
	}

	/**
	 * @return the usuarioDAO
	 */
	public UsuarioDAO getUsuarioDAO() {
		return usuarioDAO;
	}

	/**
	 * @param usuarioDAO
	 *            the usuarioDAO to set
	 */
	public void setUsuarioDAO(UsuarioDAO usuarioDAO) {
		this.usuarioDAO = usuarioDAO;
	}

	/**
	 * @return the usuario
	 */
	public Usuario getUsuario() {
		return usuario;
	}

	/**
	 * @return the administrador
	 */
	public Usuario getAdministrador() {
		return administrador;
	}

	/**
	 * Tamb�m � poss�vel injetar beans nos m�todos setters.
	 */
	@Inject
	public void setAdministrador(Usuario administrador) {
		this.administrador = administrador;
		this.administrador.setLogin("Login Admin");
	}

}
