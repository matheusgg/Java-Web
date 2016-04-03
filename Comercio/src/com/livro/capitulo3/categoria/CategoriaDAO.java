package com.livro.capitulo3.categoria;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.livro.capitulo3.util.HibernateUtil;

public class CategoriaDAO {
	private Session sessao;
	private Transaction transacao;
	
	/**
	 * Método Salvar
	 */
	public void salvar(Categoria categoria){
		try {
			this.sessao = HibernateUtil.getSessionfactory().openSession();
			this.transacao = this.sessao.beginTransaction();
			this.sessao.save(categoria);
			this.transacao.commit();
		} catch (HibernateException ex) {
			System.err.println("Erro ao salvar a categoria! " + ex.getMessage());
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
	public void atualizar(Categoria categoria){
		try{
			this.sessao = HibernateUtil.getSessionfactory().openSession();
			this.transacao = this.sessao.beginTransaction();
			this.sessao.update(categoria);
			this.transacao.commit();
		} catch (HibernateException ex) {
			System.err.println("Erro ao atualizar a categoria! " + ex.getMessage());
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
	public Categoria buscar(int codigo){
		Categoria categoria = null;		
		try{
			this.sessao = HibernateUtil.getSessionfactory().openSession();
			this.transacao = this.sessao.beginTransaction();
			Criteria filtro = this.sessao.createCriteria(Categoria.class);
			/*
			 * Restriction.eq, cria um parâmetro de busca da categoria, no caso,
			 * o código da mesma. A classe Restriction possue vários métodos
			 * de criação de parâmetros úteis.
			 */
			filtro.add(Restrictions.eq("categoria", codigo));
			/*
			 * 
			 */
			categoria = (Categoria) filtro.uniqueResult();
			this.transacao.commit();
		} catch (HibernateException ex) {
			System.err.println("Erro ao buscar a categoria! " + ex.getMessage());
		} finally {
			try {
				if (this.sessao.isOpen()) {
					this.sessao.close();
				}
			} catch (HibernateException ex) {
				System.err.println("Erro ao encerrar a sessão! " + ex.getMessage());
			}

		}
		return categoria;
	}
	
	/**
	 * Método Listar
	 */
	@SuppressWarnings("unchecked")
	public List<Categoria> listar(){
		List<Categoria> categorias = null;
		try{
			this.sessao = HibernateUtil.getSessionfactory().openSession();
			this.transacao = this.sessao.beginTransaction();
			Criteria filtro = this.sessao.createCriteria(Categoria.class);
			categorias = filtro.list();
			this.transacao.commit();
		} catch (HibernateException ex) {
			if(this.transacao.isActive()){
				this.transacao.rollback();
			}
			System.err.println("Erro ao buscar a categoria! " + ex.getMessage());
		} finally {
			try {
				if (this.sessao.isOpen()) {
					this.sessao.close();
				}
			} catch (HibernateException ex) {
				System.err.println("Erro ao encerrar a sessão! " + ex.getMessage());
			}

		}
		return categorias;
	}

}
