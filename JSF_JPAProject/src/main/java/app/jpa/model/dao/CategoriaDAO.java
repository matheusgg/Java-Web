package app.jpa.model.dao;

import javax.persistence.EntityManager;

import app.jpa.model.Categoria;
import app.jpa.util.EntityManagerProvider;

public class CategoriaDAO {
	private EntityManager manager;

	public CategoriaDAO() {
		this.manager = EntityManagerProvider.getEntitymanagerfactory()
				.createEntityManager();
	}

	public void salva(Categoria categoria) {
		this.manager.getTransaction().begin();
		this.manager.persist(categoria);
		this.manager.getTransaction().commit();

	}

	public void deleta(Categoria categoria) {
		this.manager.getTransaction().begin();
		categoria = this.manager.merge(categoria);
		this.manager.remove(categoria);
		this.manager.getTransaction().commit();

	}

	public void atualiza(Categoria categoria) {
		this.manager.getTransaction().begin();
		/*
		 * Esta linha serve para associar o objeto ao contexto da transação
		 * atual
		 */
		categoria = this.manager.merge(categoria);
		this.manager.persist(categoria);
		this.manager.getTransaction().commit();

	}
	
	public Categoria buscar(Categoria categoria){
		this.manager.getTransaction().begin();
		categoria = this.manager.find(Categoria.class, categoria.getIdCategoria());
		this.manager.getTransaction().commit();
		return categoria;
	}

}
