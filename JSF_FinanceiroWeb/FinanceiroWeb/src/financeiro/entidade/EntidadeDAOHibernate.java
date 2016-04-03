package financeiro.entidade;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class EntidadeDAOHibernate implements EntidadeDAO {
	private Session session;

	public void setSession(Session session) {
		this.session = session;
	}

	@Override
	public void salvar(Entidade entidade) {
		this.session.save(entidade);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Entidade> listar(String entidade) {
		Criteria filtro = this.session.createCriteria(Entidade.class);
		filtro.add(Restrictions.like("nome", entidade + "%"));
		return filtro.list();
	}

	@Override
	public Entidade buscar(String entidade) {
		Criteria filtro = this.session.createCriteria(Entidade.class);
		filtro.add(Restrictions.eq("nome", entidade));
		return (Entidade) filtro.uniqueResult();
	}

}
