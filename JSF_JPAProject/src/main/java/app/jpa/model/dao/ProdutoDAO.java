package app.jpa.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import app.jpa.model.Produto;
import app.jpa.util.EntityManagerProvider;

public class ProdutoDAO {
	private EntityManager manager;

	public ProdutoDAO() {
		this.manager = EntityManagerProvider.getEntitymanagerfactory()
				.createEntityManager();
	}

	public void salva(Produto produto) {
		this.manager.getTransaction().begin();
		this.manager.persist(produto);
		this.manager.getTransaction().commit();

	}

	public void deleta(Produto produto) {
		this.manager.getTransaction().begin();
		produto = this.manager.merge(produto);
		this.manager.remove(produto);
		this.manager.getTransaction().commit();

	}

	public void atualiza(Produto produto) {
		this.manager.getTransaction().begin();
		/*
		 * Esta linha serve para associar o objeto ao contexto da transação
		 * atual
		 */
		produto = this.manager.merge(produto);
		this.manager.persist(produto);
		this.manager.getTransaction().commit();

	}

	@SuppressWarnings("unchecked")
	public List<Produto> listaProdutos() {
		this.manager.getTransaction().begin();
		/*
		 * Utilizando o Hibernate como implementação do JPA, é possível utilizar
		 * a HQL
		 */
		Query query = this.manager.createQuery("from Produto");
		return query.getResultList();
	}

}
