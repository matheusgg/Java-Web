package financeiro.bolsa.acao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import financeiro.usuario.Usuario;

public class AcaoDAOHibernate implements AcaoDAO {
	private Session session;

	public void setSession(Session session) {
		this.session = session;
	}

	@Override
	public void salvar(Acao acao) {
		this.session.saveOrUpdate(acao);

	}

	@Override
	public void excluir(Acao acao) {
		this.session.delete(acao);

	}

	@Override
	public Acao carregar(String codigo) {
		return (Acao) this.session.get(Acao.class, codigo);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Acao> listar(Usuario usuario) {
		Criteria filtro = this.session.createCriteria(Acao.class);
		filtro.add(Restrictions.eq("usuario", usuario));
		return filtro.list();
	}

}
