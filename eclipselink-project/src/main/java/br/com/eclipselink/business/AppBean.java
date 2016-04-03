package br.com.eclipselink.business;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.eclipselink.model.PessoaFisica;

@Stateless
public class AppBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8347529141060918403L;

	@PersistenceContext
	private EntityManager em;

	public List<PessoaFisica> pesquisaPessoasFisicas() {
		return this.em.createNamedQuery("PessoaFisica.findAll", PessoaFisica.class).getResultList();
	}

}
