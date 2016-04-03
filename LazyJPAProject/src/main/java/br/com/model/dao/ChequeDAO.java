package br.com.model.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.model.Cheque;
import br.com.paginacao.PaginatorList;

public class ChequeDAO {
	private EntityManager manager;

	@SuppressWarnings("unchecked")
	public PaginatorList<Cheque> buscaChequesComDataLimite(int startIndex, int pageSize, HashMap<String, String> filtros) throws ParseException {
		this.manager = EntityFactory.getEmf().createEntityManager();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		String[] datas = this.buscaDatasLimite();

		Query countQuery = this.manager.createNamedQuery("countBuscaComDataLimite");
		countQuery.setParameter("dataInicial", format.parse(datas[0]));
		countQuery.setParameter("dataFinal", format.parse(datas[1]));

		Long count = (Long) countQuery.getSingleResult();

		Query chequeQuery = this.manager.createNamedQuery("buscaComDataLimite");
		chequeQuery.setParameter("dataInicial", format.parse(datas[0]));
		chequeQuery.setParameter("dataFinal", format.parse(datas[1]));
		chequeQuery.setFirstResult(startIndex);
		chequeQuery.setMaxResults(pageSize);

		List<Cheque> lista = chequeQuery.getResultList();
		PaginatorList<Cheque> cheques = new PaginatorList<Cheque>();
		cheques.setRowCount(count.intValue());
		for (Cheque cheque : lista) {
			cheques.add(cheque);
		}
		return cheques;
	}

	@SuppressWarnings("unchecked")
	public PaginatorList<Cheque> buscaChequesComFiltro(int startIndex, int pageSize, HashMap<String, String> filtros) {
		this.manager = EntityFactory.getEmf().createEntityManager();

		String dataInicial = null;
		String dataFinal = null;
		if (filtros.containsKey("dataInicial") && filtros.containsKey("dataFinal")) {
			dataInicial = filtros.get("dataInicial");
			dataFinal = filtros.get("dataFinal");
		} else {
			String[] datas = this.buscaDatasLimite();
			dataInicial = datas[0];
			dataFinal = datas[1];
		}

		StringBuilder query = new StringBuilder("select count(c.id) from Cheque c where c.dataPagamento between '" + dataInicial + "' and '"
				+ dataFinal + "'");
		for (String key : filtros.keySet()) {
			Object value = filtros.get(key);
			if (!key.equals("dataInicial") && !key.equals("dataFinal") && value != null) {
				query.append(" and " + key + " = '" + value + "'");
			}
		}

		Query countQuery = this.manager.createQuery(query.toString());
		Long count = (Long) countQuery.getSingleResult();

		Query chequeQuery = this.manager.createQuery(query.toString().replace("count(c.id)", "c"));
		
		if(count.intValue() <= 5){
			startIndex = 0;
		}
		chequeQuery.setFirstResult(startIndex);
		chequeQuery.setMaxResults(pageSize);
		List<Cheque> lista = chequeQuery.getResultList();

		PaginatorList<Cheque> cheques = new PaginatorList<Cheque>();
		cheques.setRowCount(count.intValue());
		for (Cheque cheque : lista) {
			cheques.add(cheque);
		}
		return cheques;

	}

	private String[] buscaDatasLimite() {
		Calendar cal = new GregorianCalendar();
		cal.set(Calendar.DAY_OF_MONTH, 10);
		cal.set(Calendar.MONTH, 11);
		cal.set(Calendar.YEAR, 2012);

		Date dataMaxima = cal.getTime();

		cal.set(Calendar.DAY_OF_MONTH, 10);
		cal.set(Calendar.MONTH, 2);
		Date dataMinima = cal.getTime();

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		return new String[] { format.format(dataMinima), format.format(dataMaxima) };
	}

}
