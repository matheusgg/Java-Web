package servicosexternos.ejb;

import java.io.Serializable;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Usuario;

/**
 * Os metodos dos objetos EJB de sessao sao automaticamente transacionais, por
 * este motivo um servidor de aplicacoes Java EE consegue controlar as
 * transacoes com o banco de dados. Outro detalhe importante e que quando um
 * objeto e passado de um EJB sem estado (Stateless) para o bean gerenciado do
 * JSF, este objeto se torna detached, pois o gerenciador de entidades nao
 * possui mais nenhuma informacao sobre este objeto, por este motivo, deve-se
 * anexar o objeto novamente ao contexto de persistencia atraves do metodo
 * merge.
 * 
 * @author Matheus
 * 
 */
@Stateless
public class UsuarioDao implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 422539496207732531L;

	@PersistenceContext
	private EntityManager em;

	public void save(Usuario usuario) {
		this.em.persist(usuario);
	}

}
