package br.com.spring.model.dao;

import javax.enterprise.context.RequestScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import br.com.spring.model.Usuario;

@Component
@RequestScoped
@Transactional(rollbackOn = Exception.class)
public class UsuarioDAO {

	@PersistenceContext
	private EntityManager em;

	public Usuario findUserByLogin(String login) {
		return (Usuario) this.em.createQuery("from Usuario where nome = :login").setParameter("login", login)
				.getSingleResult();
	}

	public void save(Usuario usuario) {
		this.em.persist(usuario);
	}

}
