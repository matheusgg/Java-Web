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
 * Esta anotação permite que este bean esteja disponível para utilização nas
 * EL's do jsf. Para utilização do CDI, é necessário a criação do arquivo
 * beans.xml. Este arquivo é responsável por informar o container que esta
 * aplicação possui beans CDI. Desta forma, o jsf conseguirá resolver esta
 * instância mesmo sem a anotação '@ManagedBean'. A anotação '@SessionScoped' do
 * pacote javax.enterprise.context foi utilizada pois foi projetada para
 * utilização junto com '@Named' e CDI, porém para sua utilização é preciso
 * implementar a interface Serializable na classe e em todos os objetos que
 * compões esta classe. Além disso, é necessário que este ManagedBean possua um
 * construtor padrão.
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
	 * É possível utilizar injeção de dependência nos cnstrutores. Desta forma,
	 * uma implementação de Usuário será procurada para ser injetada neste ponto
	 * de injeção.
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
	 * '@Inject' é responsável por injetar um componente CDI. Com ela, não é
	 * necessário informar outros parâmetros, pois o próprio CDI se encarrega
	 * encontrar uma implementação para esta interface. '@New' indica qual
	 * implementação deve ser utilizada para injeção. Isto é necessário quando
	 * existe mais de uma implementação, deste modo, o CDI não consegue
	 * determinar qual implementação ele deve utilizar para injeção. Com '@New',
	 * dizemos ao CDI que ignore as outras implementações e utilize
	 * UsuarioDAOImpl3. Já a anotação'@Qualificador' indica que a implementação
	 * que estiver anotada com '@Qualificador' será utilizada para injeção.
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
	 * Também é possível injetar beans nos métodos setters.
	 */
	@Inject
	public void setAdministrador(Usuario administrador) {
		this.administrador = administrador;
		this.administrador.setLogin("Login Admin");
	}

}
