package app.model.dao;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import app.model.Cliente;
import app.util.HibernateUtil;

public class ClienteDAO {
	private Session sessao;
	private Logger logger;
	
	public ClienteDAO(){
		this.logger = Logger.getLogger(ClienteDAO.class);
	}

	public void salva(Cliente cliente) {
		this.logger.info("Salvando o cliente...");
		this.sessao = HibernateUtil.openSession();
		this.sessao.save(cliente);
		HibernateUtil.commit(this.sessao);		
	}
	
	public void atualiza(Cliente cliente) {
		this.logger.info("Atualizando o cliente...");
		this.sessao = HibernateUtil.openSession();
		this.sessao.update(cliente);
		HibernateUtil.commit(this.sessao);
	}
	
	public void deleta(Cliente cliente) {
		this.logger.info("Deletando o cliente...");
		this.sessao = HibernateUtil.openSession();
		this.sessao.delete(cliente);
		HibernateUtil.commit(this.sessao);
	}
	
	public Cliente busca(String nome) {
		this.logger.info("Buscando o cliente...");
		this.sessao = HibernateUtil.openSession();
		Criteria filtro = this.sessao.createCriteria(Cliente.class);
		filtro.add(Restrictions.eq("nome", nome));
		Cliente cliente = (Cliente) filtro.uniqueResult();
		return cliente;
	}

}
