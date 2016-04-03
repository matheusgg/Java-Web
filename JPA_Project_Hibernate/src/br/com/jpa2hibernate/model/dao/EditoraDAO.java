package br.com.jpa2hibernate.model.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.com.jpa2hibernate.factory.ConnectionFactory;
import br.com.jpa2hibernate.model.Editora;

public class EditoraDAO {
	private EntityManager manager;
	
	public EditoraDAO(){
		this.manager = ConnectionFactory.getEntityManagerFactory().createEntityManager();
	}
	
	/**
	 * Inclusão.
	 * @param editora
	 */
	public void salva(Editora editora){
		this.manager.getTransaction().begin();
		this.manager.persist(editora);
		this.manager.getTransaction().commit();
	}
	
	/**
	 * Alteração.
	 * @param editora
	 */
	public void altera(Editora editora){
		this.manager.getTransaction().begin();
		this.manager.getTransaction().commit();
	}
	
	/**
	 * Exclusão.
	 * @param editora
	 */
	public void exlui(Editora editora){
		this.manager.getTransaction().begin();
		this.manager.merge(editora);
		this.manager.remove(editora);
		this.manager.getTransaction().commit();
	}
	
	/**
	 * Pesquisa utilizando find.
	 * @param id
	 * @return
	 */
	public Editora busca(int id){
		this.manager.getTransaction().begin();
		Editora e = this.manager.find(Editora.class, id);
		this.manager.getTransaction().commit();
		return e;
	}
	
	/**
	 * Pesquisa utilizando criteria.
	 * @param propriedade
	 * @param valor
	 * @param tipo
	 * @return
	 */
	public List<Editora> lista(String propriedade, Object valor, Class<?> tipo){
		this.manager.getTransaction().begin();
		CriteriaBuilder builder = this.manager.getCriteriaBuilder();
		CriteriaQuery<Editora> query = builder.createQuery(Editora.class);
		Root<Editora> from = query.from(Editora.class);
		TypedQuery<Editora> typedQuery = this.manager.createQuery(query.select(from).where(builder.equal(from.get(propriedade).as(tipo), valor)));
		List<Editora> lista = typedQuery.getResultList();
		this.manager.getTransaction().commit();
		return lista;
	}
	
	/**
	 * Pesquisa o maior valor utilizando criteria.
	 * @param propriedade
	 * @param valor
	 * @param tipo
	 * @return
	 */
	public List<Editora> buscaMaior(String propriedade, Integer valor, Class<? extends Number> tipo){
		this.manager.getTransaction().begin();
		CriteriaBuilder builder = this.manager.getCriteriaBuilder();
		CriteriaQuery<Editora> query = builder.createQuery(Editora.class);
		Root<Editora> from = query.from(Editora.class);
		TypedQuery<Editora> typedQuery = this.manager.createQuery(query.select(from).where(builder.gt(from.get(propriedade).as(tipo), valor)));
		List<Editora> lista = typedQuery.getResultList();
		this.manager.getTransaction().commit();
		return lista;
	}
	
	/**
	 * Lista os registros utilizando uma query simples (HQL)
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Editora> lista(int id){
		this.manager.getTransaction().begin();
		Query query = this.manager.createQuery("from Editora e");
		List<Editora> lista = query.getResultList();
		this.manager.getTransaction().commit();
		return lista;
	}
	
	

}
