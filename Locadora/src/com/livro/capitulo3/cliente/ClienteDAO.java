package com.livro.capitulo3.cliente;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.livro.capitulo3.util.HibernateUtil;

public class ClienteDAO {
	private Session sessao;
	private Transaction transacao;

	/**
	 * Método salvar
	 * @param cliente
	 */
	public void salvar(Cliente cliente) {
		try {
			this.sessao = HibernateUtil.getSessionfactory().openSession();
			this.transacao = sessao.beginTransaction();
			this.sessao.save(cliente);
			this.transacao.commit();
		} catch (HibernateException ex) {
			System.err.println("Erro ao salvar o cliente! " + ex.getMessage());
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
	public void atualizar(Cliente cliente){
		try{
			this.sessao = HibernateUtil.getSessionfactory().openSession();
			this.transacao = sessao.beginTransaction();
			this.sessao.update(cliente);
			this.transacao.commit();
		} catch (HibernateException ex) {
			System.err.println("Erro ao atualizar o cliente! " + ex.getMessage());
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
	public void excluir(Cliente cliente){
		try{
			this.sessao = HibernateUtil.getSessionfactory().openSession();
			this.transacao = sessao.beginTransaction();
			this.sessao.delete(cliente);
			this.transacao.commit();
		} catch (HibernateException ex) {
			System.err.println("Erro ao excluir o cliente! " + ex.getMessage());
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
	public Cliente buscarCliente(int codigo){
		Cliente cliente = null;
		try{
			this.sessao = HibernateUtil.getSessionfactory().openSession();
			this.transacao = sessao.beginTransaction();	
			Criteria filtro = this.sessao.createCriteria(Cliente.class);
			filtro.add(Restrictions.eq("cliente", codigo));
			cliente = (Cliente) filtro.uniqueResult();
			this.transacao.commit();
		} catch (HibernateException ex) {
			System.err.println("Erro ao buscar o cliente! " + ex.getMessage());
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
		return cliente;
	}
	
	/**
	 * Método Listar
	 */
	@SuppressWarnings("unchecked")
	public List<Cliente> listar(){
		List<Cliente> clientes = null;
		try{
			this.sessao = HibernateUtil.getSessionfactory().openSession();
			this.transacao = sessao.beginTransaction();
			Criteria filtro = this.sessao.createCriteria(Cliente.class);
			clientes = filtro.list();
			this.transacao.commit();
		}catch(HibernateException ex){
			System.err.println("Erro ao listar os clientes! " + ex.getMessage());
		}finally{
			try{
				if(this.sessao.isOpen()){
					this.sessao.close();
				}
			}catch(HibernateException ex){
				System.err.println("Erro ao encerrar a sessão! " + ex.getMessage());
			}
		}
		return clientes;
	}	
}
