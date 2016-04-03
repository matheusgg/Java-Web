package com.livro.capitulo3.produto;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.livro.capitulo3.produto.Produto;
import com.livro.capitulo3.util.HibernateUtil;

public class ProdutoDAO {
	private Session sessao;
	private Transaction transacao;
	
	/**
	 * Método Salvar
	 */
	public void salvar(Produto produto){
		try {
			this.sessao = HibernateUtil.getSessionfactory().openSession();
			this.transacao = this.sessao.beginTransaction();
			this.sessao.save(produto);
			this.transacao.commit();
		} catch (HibernateException ex) {
			System.err.println("Erro ao salvar o produto! " + ex.getMessage());
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
	public void atualizar(Produto produto){
		try{
			this.sessao = HibernateUtil.getSessionfactory().openSession();
			this.transacao = this.sessao.beginTransaction();
			this.sessao.update(produto);
			this.transacao.commit();
		} catch (HibernateException ex) {
			System.err.println("Erro ao atualizar o produto! " + ex.getMessage());
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
	public Produto buscar(int codigo){
		Produto produto = null;
		try{
			this.sessao = HibernateUtil.getSessionfactory().openSession();
			this.transacao = this.sessao.beginTransaction();
			Criteria filtro = this.sessao.createCriteria(Produto.class);
			filtro.add(Restrictions.eq("produto", codigo));
			produto = (Produto) filtro.uniqueResult();
			this.transacao.commit();
		} catch (HibernateException ex) {
			System.err.println("Erro ao buscar o produto! " + ex.getMessage());
		} finally {
			try {
				if (this.sessao.isOpen()) {
					this.sessao.close();
				}
			} catch (HibernateException ex) {
				System.err.println("Erro ao encerrar a sessão! " + ex.getMessage());
			}

		}
		return produto;
	}
	
	/**
	 * Método Listar
	 */
	@SuppressWarnings("unchecked")
	public List<Produto> listar(){
		List<Produto> produtos = null;
		try{
			this.sessao = HibernateUtil.getSessionfactory().openSession();
			this.transacao = this.sessao.beginTransaction();
			Criteria filtro = this.sessao.createCriteria(Produto.class);
			produtos = filtro.list();
			this.transacao.commit();
		} catch (HibernateException ex) {
			if(this.transacao.isActive()){
				this.transacao.rollback();
			}
			System.err.println("Erro ao buscar o produto! " + ex.getMessage());
		} finally {
			try {
				if (this.sessao.isOpen()) {
					this.sessao.close();
				}
			} catch (HibernateException ex) {
				System.err.println("Erro ao encerrar a sessão! " + ex.getMessage());
			}

		}
		return produtos;
	}
}
