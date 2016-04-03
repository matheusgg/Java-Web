package financeiro.conta;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import financeiro.usuario.Usuario;

public class ContaDAOHibernate implements ContaDAO {
	private Session session;

	@Override
	public void salvar(Conta conta) {
		/*
		 * Utilizando o método seveOrUpdate do Session, se o campo Id da classe mapeada estiver nulo
		 * o Hibernate fará o insert no banco, porém se o Id não estiver nulo, o Hibernate tentará
		 * realizar o update.
		 */
		this.session.saveOrUpdate(conta);
	}

	@Override
	public void excluir(Conta conta) {
		this.session.delete(conta);
	}

	@Override
	public Conta carregar(Integer conta) {
		return (Conta) this.session.get(Conta.class, conta);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Conta> listar(Usuario usuario) {
		Criteria filtro = this.session.createCriteria(Conta.class);
		filtro.add(Restrictions.eq("usuario", usuario));
		return filtro.list();
	}

	@Override
	public Conta buscarFavorita(Usuario usuario) {
		Criteria filtro = this.session.createCriteria(Conta.class);
		filtro.add(Restrictions.eq("usuario", usuario));
		filtro.add(Restrictions.eq("favorita", true));
		return (Conta) filtro.uniqueResult();
	}

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

}
