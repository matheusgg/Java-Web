package app.jpa.model.dao;

import javax.persistence.EntityManager;

import app.jpa.model.Cliente;
import app.jpa.util.EntityManagerProvider;

public class ClienteDAO {
	private EntityManager manager;

	public ClienteDAO() {
		this.manager = EntityManagerProvider.getEntitymanagerfactory()
				.createEntityManager();
	}

	public void salva(Cliente cliente) {
		this.manager.getTransaction().begin();
		this.manager.persist(cliente);
		this.manager.getTransaction().commit();

	}

	public void deleta(Cliente cliente) {
		this.manager.getTransaction().begin();
		cliente = this.manager.merge(cliente);
		this.manager.remove(cliente);
		this.manager.getTransaction().commit();

	}

	public void atualiza(Cliente cliente) {
		this.manager.getTransaction().begin();
		/*
		 * Esta linha serve para associar o objeto ao contexto da transação
		 * atual
		 */
		cliente = this.manager.merge(cliente);
		this.manager.persist(cliente);
		this.manager.getTransaction().commit();

	}
	
	public Cliente busca(Cliente cliente){
		this.manager.getTransaction().begin();
		cliente = this.manager.find(Cliente.class, cliente.getIdCliente());
		this.manager.getTransaction().commit();
		return cliente;
	}

}
