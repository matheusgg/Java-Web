package br.com.cdi.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import br.com.cdi.model.Cliente;

/**
 * Automaticamente todos os métodos de um EJB's sao transacionais, entao era comum
 * um DAO ser marcado com Stateless para se utilizar dos beneficios do EJB
 * trazidos pelo container. Com o Java EE 7 uma nova anotacao foi introduzida,
 * Transaction, que permite a utilizacao de todos os beneficios de controle de
 * transacao trazidos pelos containers. Desta forma, nao é mais necessário
 * sobrecarregar a aplicacao criando EJB's.
 * 
 * @author Matheus
 *
 */
public class ClienteDAO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4849669385538333397L;

	@PersistenceContext
	private EntityManager em;

	public List<Cliente> listaClientes() {
		return null;
	}

	public Cliente findCliente(Long id) {
		return this.em.find(Cliente.class, id);
	}

	/**
	 * Transactional pode ser aplicada para metodos ou tipos.
	 * 
	 * @param cliente
	 */
	@Transactional
	public void save(Cliente cliente) {
		this.em.persist(cliente);
	}

}
