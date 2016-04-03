package br.com.cdi.factory;

import java.io.Serializable;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import br.com.cdi.qualificadores.Remetente;

/**
 * Classe responsável pelo controle de criacao e destruicao dos EntityManagers.
 * Pode ser utilizada quando a transacao nao é controlada pelo servidor. Com
 * Named e com o escopo de aplciacao, o container criara apenas uma instancia
 * dessa classe.
 * 
 * @author Matheus
 *
 */
@Named
@ApplicationScoped
public class Factory implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3292950109100147351L;

	private static final EntityManagerFactory EMF = Persistence.createEntityManagerFactory("CDIProject");

	/**
	 * Com Produces, o container ira inferir sobre o tipo de retorno e toda vez
	 * que um objeto deste tipo for injetado este método será chamado. Neste
	 * caso, este objeto terá o escopo de Requisicao, ou seja, enquanto a
	 * requisicao estiver persistindo este objeto estará disponível. Caso
	 * nenhuma anotacao de escopo seja especificada, o escopo de Aplicacao é
	 * assumido.
	 * 
	 * @return
	 */
	// @Produces
	// @RequestScoped
	public EntityManager createEntityManager() {
		EntityManager em = Factory.EMF.createEntityManager();
		em.getTransaction().begin();
		return em;
	}

	/**
	 * O container ira inferir sobre o tipo do parametro anotado com Disposes, e
	 * quando um objeto dentro do escopo estiver prestes a ser destruido, este
	 * método será chamado.
	 * 
	 * @param em
	 */
	public void closeEntityManager(/* @Disposes */EntityManager em) {
		if (em != null && em.isOpen()) {
			em.close();
		}
	}

	@Produces
	@Remetente
	public String createRemetente() {
		return "matheus.ggoes@outlook.com";
	}

}
