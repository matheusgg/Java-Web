package dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.StoredProcedureQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;

import model.Cliente;

public class DAO {

	private EntityManager em;

	public DAO() {
		this.em = Persistence.createEntityManagerFactory("coreJSF").createEntityManager();
	}

	@SuppressWarnings("unchecked")
	public List<Cliente> search() {
		List<Cliente> clientes = this.em.createNamedQuery("Cliente.search").getResultList();
		return clientes;
	}

	public Cliente pesquisaComFuncao(int param) {
		Query query = this.em.createNamedQuery("Cliente.searchFunction");
		query.setParameter("param", param);
		return (Cliente) query.getSingleResult();
	}

	/**
	 * Atualizando registros com criteria
	 */
	public void updateComCriteria() {
		this.em.getTransaction().begin();

		CriteriaBuilder builder = this.em.getCriteriaBuilder();
		CriteriaUpdate<Cliente> criteriaUpdate = builder.createCriteriaUpdate(Cliente.class);
		Root<Cliente> root = criteriaUpdate.from(Cliente.class);
		criteriaUpdate.set(root.get("nome"), "Matheus Gomes").where(builder.equal(root.get("id"), 1));

		Query query = this.em.createQuery(criteriaUpdate);
		query.executeUpdate();

		this.em.getTransaction().commit();
	}

	/**
	 * Removendo registros com criteria
	 */
	public void deleteComCriteria() {
		this.em.getTransaction().begin();

		CriteriaBuilder builder = this.em.getCriteriaBuilder();
		CriteriaDelete<Cliente> criteriaDelete = builder.createCriteriaDelete(Cliente.class);
		Root<Cliente> root = criteriaDelete.from(Cliente.class);
		criteriaDelete.where(builder.gt(root.get("id").as(Number.class), 1));

		Query query = this.em.createQuery(criteriaDelete);
		query.executeUpdate();

		this.em.getTransaction().commit();
	}

	/**
	 * Chamando uma stored procedure atraves de uma NamedStoredProcedureQuery
	 * 
	 * @param nome
	 * @param nascimento
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Cliente> callStoredProcedureComNamedStoredProcedureQuery(String nome, Date nascimento) {
		StoredProcedureQuery storedProcedureQuery = this.em.createNamedStoredProcedureQuery("insertAndSearch");
		storedProcedureQuery.setParameter("nome", nome);
		storedProcedureQuery.setParameter("nascimento", nascimento);
		storedProcedureQuery.execute();

		List<Cliente> clientes = storedProcedureQuery.getResultList();
		return clientes;
	}

	/**
	 * Chamando uma stored procedure criando os parametros de entrada
	 * dinamicamente
	 * 
	 * @param nome
	 * @param nascimento
	 * @return
	 */
	public void storedProceduresCriadasDinamicamente(String nome, Date nascimento) {
		StoredProcedureQuery storedProcedureQuery = this.em.createStoredProcedureQuery("insere", int.class);

		// Registrando os parametros dinamicamente
		storedProcedureQuery.registerStoredProcedureParameter("nome", String.class, ParameterMode.IN);
		storedProcedureQuery.registerStoredProcedureParameter("nascimento", Date.class, ParameterMode.IN);

		// Setando os parametros registrados
		storedProcedureQuery.setParameter("nome", nome);
		storedProcedureQuery.setParameter("nascimento", nascimento);

		storedProcedureQuery.execute();
	}

	/**
	 * Com esta nova funcionalidade, e possivel escrever queries nativas em SQL
	 * e converter os resultados diretamente para objetos mapeados. Utulizando o
	 * construtor de resultados, os objetos serao criados pela invocacao do
	 * construtor que receba os parametros informados.
	 * 
	 * @return
	 */
	public Cliente mapeamentoDeConstrutorDeResultados() {
		Query query = this.em.createNativeQuery("select c.id as ID, c.nome as NOME from Cliente c where id = 1", "ClienteResult");
		return (Cliente) query.getSingleResult();
	}

	/**
	 * Aqui os objetos serao criados atraves do construtor default e os falores
	 * serao definidos diretamento nos campos da classe.
	 * 
	 * @return
	 */
	public Cliente mapeamentoDeConstrutorDeResultadosEmbutido() {
		Query query = this.em.createNativeQuery("select c.id as ID, c.nome as NOME from Cliente c where id = 1", "ClienteFieldResult");
		return (Cliente) query.getSingleResult();
	}
	
	public void insereCliente(Cliente cliente){
		this.em.getTransaction().begin();
		this.em.persist(cliente);
		this.em.getTransaction().commit();
	}

}
