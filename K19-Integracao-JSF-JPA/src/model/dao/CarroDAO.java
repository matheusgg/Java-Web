package model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import model.Carro;
import filters.JPAFilter;

public class CarroDAO {
	private EntityManager manager;

	public CarroDAO() {
		this.manager = JPAFilter.getEntityManager();
	}

	public void adiciona(Carro carro) {
		this.manager.persist(carro);
	}

	@SuppressWarnings("unchecked")
	public List<Carro> buscaTodos() {
		Query query = this.manager.createQuery("select x from Carro x");
		return query.getResultList();
	}
	
	public void remove(Carro carro){
		this.manager.remove(carro);
	}

}
