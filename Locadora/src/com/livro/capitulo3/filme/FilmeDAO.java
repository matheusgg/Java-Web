package com.livro.capitulo3.filme;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.livro.capitulo3.util.HibernateUtil;

public class FilmeDAO {
	private Session sessao;
	private Transaction transacao;

	/**
	 * Método salvar
	 * @param filme
	 */
	public void salvar(Filme filme) {
		try {
			this.sessao = HibernateUtil.getSessionfactory().openSession();
			this.transacao = sessao.beginTransaction();
			this.sessao.save(filme);
			this.transacao.commit();
		} catch (HibernateException ex) {
			System.err.println("Erro ao salvar o filme! " + ex.getMessage());
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
	public void atualizar(Filme filme){
		try{
			this.sessao = HibernateUtil.getSessionfactory().openSession();
			this.transacao = sessao.beginTransaction();
			this.sessao.update(filme);
			this.transacao.commit();
		} catch (HibernateException ex) {
			System.err.println("Erro ao atualizar o filme! " + ex.getMessage());
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
	public void excluir(Filme filme){
		try{
			this.sessao = HibernateUtil.getSessionfactory().openSession();
			this.transacao = sessao.beginTransaction();
			this.sessao.delete(filme);
			this.transacao.commit();
		} catch (HibernateException ex) {
			System.err.println("Erro ao excluir o filme! " + ex.getMessage());
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
	public Filme buscarFilme(int codigo){
		Filme filme = null;
		try{
			this.sessao = HibernateUtil.getSessionfactory().openSession();
			this.transacao = sessao.beginTransaction();	
			Criteria filtro = this.sessao.createCriteria(Filme.class);
			filtro.add(Restrictions.eq("filme", codigo));
			filme = (Filme) filtro.uniqueResult();
			this.transacao.commit();
		} catch (HibernateException ex) {
			System.err.println("Erro ao buscar o filme! " + ex.getMessage());
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
		return filme;
	}
	
	/**
	 * Método Listar
	 */
	@SuppressWarnings("unchecked")
	public List<Filme> listar(){
		List<Filme> filmes = null;
		try{
			this.sessao = HibernateUtil.getSessionfactory().openSession();
			this.transacao = sessao.beginTransaction();
			Criteria filtro = this.sessao.createCriteria(Filme.class);
			filmes = filtro.list();
			this.transacao.commit();
		}catch(HibernateException ex){
			System.err.println("Erro ao listar os filmes! " + ex.getMessage());
		}finally{
			try{
				if(this.sessao.isOpen()){
					this.sessao.close();
				}
			}catch(HibernateException ex){
				System.err.println("Erro ao encerrar a sessão! " + ex.getMessage());
			}
		}
		return filmes;
	}	
}
