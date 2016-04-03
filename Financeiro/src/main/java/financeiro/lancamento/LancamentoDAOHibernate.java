package financeiro.lancamento;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import financeiro.conta.Conta;

public class LancamentoDAOHibernate implements LancamentoDAO {
	private Session session;

	public void setSession(Session session) {
		this.session = session;
	}

	@Override
	public void salvar(Lancamento lancamento) {
		this.session.saveOrUpdate(lancamento);

	}

	@Override
	public void excluir(Lancamento lancamento) {
		this.session.delete(lancamento);

	}

	@Override
	public Lancamento caregar(Integer lancamento) {
		return (Lancamento) this.session.get(Lancamento.class, lancamento);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Lancamento> listar(Conta conta, Date dataInicio, Date dataFim) {
		Criteria filtro = this.session.createCriteria(Lancamento.class);

		if (dataInicio != null && dataFim != null) {
			filtro.add(Restrictions.between("data", dataInicio, dataFim));
		} else if (dataInicio != null) {
			/*
			 * Restrictions.ge aplica um filtro maior ou igual que a dataInicio
			 * (greater than or equal)
			 */
			filtro.add(Restrictions.ge("data", dataInicio));
		} else if (dataFim != null) {
			/*
			 * Restrictions.le aplica um filtro menor ou igual que a dataInicio
			 * (less than or equal)
			 */
			filtro.add(Restrictions.le("data", dataFim));
		}

		filtro.add(Restrictions.eq("conta", conta));
		filtro.addOrder(Order.asc("data"));
		return filtro.list();
	}

	@Override
	public float saldo(Conta conta, Date data) {
		StringBuilder sql = new StringBuilder();
		sql.append("select sum(l.valor * c.fator)");
		sql.append(" from LANCAMENTO l,");
		sql.append(" CATEGORIA c");
		sql.append(" where l.categoria = c.codigo");
		sql.append(" and l.conta = :conta");
		sql.append(" and l.data <= :data");

		SQLQuery query = this.session.createSQLQuery(sql.toString());
		query.setParameter("conta", conta.getConta());
		query.setParameter("data", data);

		BigDecimal saldo = (BigDecimal) query.uniqueResult();

		if (saldo != null) {
			return saldo.floatValue();
		}
		return 0f;
	}

}
