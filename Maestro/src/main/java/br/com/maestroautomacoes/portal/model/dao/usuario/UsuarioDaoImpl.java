package br.com.maestroautomacoes.portal.model.dao.usuario;

import org.hibernate.Query;

import br.com.maestroautomacoes.portal.model.dao.util.AbstractDao;
import br.com.maestroautomacoes.portal.model.dao.util.Dao;
import br.com.maestroautomacoes.portal.model.usuario.Usuario;

@Dao
public class UsuarioDaoImpl extends AbstractDao<Usuario> implements UsuarioDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2604438825354542135L;

	@Override
	public void cadastar(Usuario usuario) {
		super.salva(usuario);
	}

	@Override
	public void alterar(Usuario usuario) {
		super.atualiza(usuario);
	}

	@Override
	public void deletar(Usuario usuario) {
		super.remove(usuario);
	}

	@Override
	public Usuario recuperaUsuario(int codigoUsuario) {
		return super.find(Usuario.class, codigoUsuario);
	}

	@Override
	public Usuario recuperaUsuario(String email, String senha) {
		Query query = super.sessao.createQuery("select user from Usuario user where user.email = :email AND user.senha = :senha");
		query.setParameter("email", email);
		query.setParameter("senha", senha);
		Usuario user = (Usuario) query.uniqueResult();
		return user;
	}

	@Override
	public Usuario recuperaUsuario(String email) {
		Query query = this.sessao.createQuery("select user from Usuario user where user.email = :email");
		query.setParameter("email", email);
		return (Usuario) query.uniqueResult();
	}

}
