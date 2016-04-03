package com.livro.capitulo3.locacao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.livro.capitulo3.util.HibernateUtil;

public class LocacaoDAO {
	private Session sessao;
	private Transaction transacao;

	/**
	 * Método salvar
	 * @param locacao
	 */
	public void salvar(Locacao locacao) {
		try {
			this.sessao = HibernateUtil.getSessionfactory().openSession();
			this.transacao = sessao.beginTransaction();
			this.sessao.save(locacao);
			this.transacao.commit();
		} catch (HibernateException ex) {
			System.err.println("Erro ao salvar a locacao! " + ex.getMessage());
		} finally {
			try {
				if (this.sessao.isOpen()) {
					this.sessao.close();
				}
			} catch (HibernateException ex) {
				System.err.println("Erro ao encerrar a sessão! " + ex.getMessage());
			}
		}
	}
	
	/**
	 * Método Atualizar
	 */
	public void atualizar(Locacao locacao){
		try{
			this.sessao = HibernateUtil.getSessionfactory().openSession();
			this.transacao = sessao.beginTransaction();
			this.sessao.update(locacao);
			this.transacao.commit();
		} catch (HibernateException ex) {
			System.err.println("Erro ao atualizar a locacao! " + ex.getMessage());
		} finally {
			try {
				if (this.sessao.isOpen()) {
					this.sessao.close();
				}
			} catch (HibernateException ex) {
				System.err.println("Erro ao encerrar a sessão! " + ex.getMessage());
			}
		}
	}
	
	/**
	 * Método excluir
	 */
	public void excluir(Locacao locacao){
		try{
			this.sessao = HibernateUtil.getSessionfactory().openSession();
			this.transacao = sessao.beginTransaction();
			this.sessao.delete(locacao);
			this.transacao.commit();
		} catch (HibernateException ex) {
			System.err.println("Erro ao excluir a locacao! " + ex.getMessage());
		} finally {
			try {
				if (this.sessao.isOpen()) {
					this.sessao.close();
				}
			} catch (HibernateException ex) {
				System.err.println("Erro ao encerrar a sessão! " + ex.getMessage());
			}
		}
	}
	
	/**
	 * Método buscar
	 */
	public Locacao buscarLocacao(int codigo){
		Locacao locacao = null;
		try{
			this.sessao = HibernateUtil.getSessionfactory().openSession();
			this.transacao = sessao.beginTransaction();	
			Criteria filtro = this.sessao.createCriteria(Locacao.class);
			filtro.add(Restrictions.eq("locacao", codigo));
			locacao = (Locacao) filtro.uniqueResult();
			this.transacao.commit();
		} catch (HibernateException ex) {
			System.err.println("Erro ao buscar a locacao! " + ex.getMessage());
			if(this.transacao.isActive()){
				this.transacao.rollback();
			}
		} finally {
			try {
				if (this.sessao.isOpen()) {
					this.sessao.close();
				}
			} catch (HibernateException ex) {
				System.err.println("Erro ao encerrar a sessão! " + ex.getMessage());
			}
		}
		return locacao;
	}
	
	/**
	 * Método Listar
	 */
	@SuppressWarnings("unchecked")
	public List<Locacao> listar(){
		List<Locacao> locacaos = null;
		try{
			this.sessao = HibernateUtil.getSessionfactory().openSession();
			this.transacao = sessao.beginTransaction();
			Criteria filtro = this.sessao.createCriteria(Locacao.class);
			locacaos = filtro.list();
			this.transacao.commit();
		}catch(HibernateException ex){
			System.err.println("Erro ao listar as locacoes! " + ex.getMessage());
		}finally{
			try{
				if(this.sessao.isOpen()){
					this.sessao.close();
				}
			}catch(HibernateException ex){
				System.err.println("Erro ao encerrar a sessão! " + ex.getMessage());
			}
		}
		return locacaos;
	}	
}
