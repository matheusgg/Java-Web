package servicosexternos;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.sql.DataSource;
import javax.transaction.UserTransaction;

import model.Usuario;
import servicosexternos.ejb.UsuarioDao;

@Named
@ViewScoped
public class ServicosExternosBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -202400471107456532L;

	@Resource(lookup = "jdbc/app_db")
	private DataSource dataSource;

	@PersistenceUnit(unitName = "coreJSF")
	private EntityManagerFactory emf;

	@Resource
	private UserTransaction transaction;

	@EJB
	// ou @Inject
	private UsuarioDao usuarioDao;

	private Usuario usuario;

	@PostConstruct
	public void init() {
		this.usuario = new Usuario();
	}

	public void save() throws Exception {
		// this.saveDefault();
		this.saveWithEJBStateless();
	}

	private void saveWithEJBStateless() {
		this.usuarioDao.save(this.usuario);
		this.resetUser();
	}

	public void saveDefault() throws Exception {
		EntityManager em = this.emf.createEntityManager();
		this.transaction.begin();
		em.joinTransaction();
		em.persist(this.usuario);
		this.transaction.commit();
		this.resetUser();
		em.close();
	}

	private void resetUser() {
		this.usuario = new Usuario();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("O usuario foi salvo com sucesso!"));
	}

	/**
	 * @return the dataSource
	 */
	public DataSource getDataSource() {
		return dataSource;
	}

	/**
	 * @param dataSource
	 *            the dataSource to set
	 */
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	/**
	 * @return the emf
	 */
	public EntityManagerFactory getEmf() {
		return emf;
	}

	/**
	 * @param emf
	 *            the emf to set
	 */
	public void setEmf(EntityManagerFactory emf) {
		this.emf = emf;
	}

	/**
	 * @return the transaction
	 */
	public UserTransaction getTransaction() {
		return transaction;
	}

	/**
	 * @param transaction
	 *            the transaction to set
	 */
	public void setTransaction(UserTransaction transaction) {
		this.transaction = transaction;
	}

	/**
	 * @return the usuario
	 */
	public Usuario getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario
	 *            the usuario to set
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
