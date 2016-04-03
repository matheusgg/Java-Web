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
	 * M�todo salvar
	 * @param categoria
	 */
	public void salvar(Categoria categoria) {
		try {
			this.sessao = HibernateUtil.getSessionfactory().openSession();
			this.transacao = sessao.beginTransaction();
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
				System.err.println("Erro ao encerrar a sess�o! " + ex.getMessage());
			}
		}
	}
	
	/**
	 * M�todo Atualizar
	 */
	public void atualizar(Categoria categoria){
		try{
			this.sessao = HibernateUtil.getSessionfactory().openSession();
			this.transacao = sessao.beginTransaction();
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
				System.err.println("Erro ao encerrar a sess�o! " + ex.getMessage());
			}
		}
	}
	
	/**
	 * M�todo excluir
	 */
	public void excluir(Categoria categoria){
		try{
			this.sessao = HibernateUtil.getSessionfactory().openSession();
			this.transacao = sessao.beginTransaction();
			this.sessao.delete(categoria);
			this.transacao.commit();
		} catch (HibernateException ex) {
			System.err.println("Erro ao excluir a categoria! " + ex.getMessage());
		} finally {
			try {
				if (this.sessao.isOpen()) {
					this.sessao.close();
				}
			} catch (HibernateException ex) {
				System.err.println("Erro ao encerrar a sess�o! " + ex.getMessage());
			}
		}
	}
	
	/**
	 * M�todo buscar
	 */
	public Categoria buscarCategoria(int codigo){
		Categoria categoria = null;
		try{
			this.sessao = HibernateUtil.getSessionfactory().openSession();
			this.transacao = sessao.beginTransaction();			
			Criteria filtro = this.sessao.createCriteria(Categoria.class);
			filtro.add(Restrictions.eq("categoria", codigo));
			categoria = (Categoria) filtro.uniqueResult();
			this.transacao.commit();
		} catch (HibernateException ex) {
			System.err.println("Erro ao buscar a categoria! " + ex.getMessage());
			if(this.transacao.isActive()){
				this.transacao.rollback();
			}
		} finally {
			try {
				if (this.sessao.isOpen()) {
					this.sessao.close();
				}
			} catch (HibernateException ex) {
				System.err.println("Erro ao encerrar a sess�o! " + ex.getMessage());
			}
		}
		return categoria;
	}
	
	/**
	 * M�todo Listar
	 */
	@SuppressWarnings("unchecked")
	public List<Categoria> listar(){
		List<Categoria> categorias = null;
		try{
			this.sessao = HibernateUtil.getSessionfactory().openSession();
			this.transacao = sessao.beginTransaction();
			Criteria filtro = this.sessao.createCriteria(Categoria.class);
			categorias = filtro.list();
			this.transacao.commit();
		}catch(HibernateException ex){
			System.err.println("Erro ao listar as categorias! " + ex.getMessage());
		}finally{
			try{
				if(this.sessao.isOpen()){
					this.sessao.close();
				}
			}catch(HibernateException ex){
				System.err.println("Erro ao encerrar a sess�o! " + ex.getMessage());
			}
		}
		return categorias;
	}	
}
