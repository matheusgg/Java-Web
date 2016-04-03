package com.livro.capitulo3.crudxml;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.livro.capitulo3.conexao.HibernateUtil;
import com.livro.capitulo3.crudannotations.Contato;

public class ContatoCrudXML {
	/*
	 * M�todo salvar
	 */
	public void salvar(Contato contato){
		Session sessao = null; 
		Transaction transacao = null;
		try{
			sessao = HibernateUtil.getSessionfactory().openSession(); // Abre a sess�o da conex�o
			transacao = sessao.beginTransaction(); // Abre a transa��o que ser� feita

			sessao.save(contato); // Salva o contato (Objeto mapeado) no banco
			transacao.commit();
		} catch (HibernateException ex) {
			System.err.println("Erro ao salvar o contato " + ex.getMessage());
		}finally{
			try{
				sessao.close();
			}catch(HibernateException ex){
				System.err.println("Erro ao fechar a sess�o "+ ex.getMessage());
			}
		}
	}
	
	/*
	 * M�todo atualizar
	 */
	public void atualizar(Contato contato){
		Session sessao = null;
		Transaction transacao = null;
		
		try{
			sessao = HibernateUtil.getSessionfactory().openSession();
			transacao = sessao.beginTransaction();
			
			sessao.update(contato);
			transacao.commit();
		}catch(HibernateException ex){
			System.err.println("Erro ao atualizar o contato " + ex.getMessage());
		}finally{
			try{
				sessao.close();
			}catch(HibernateException ex){
				System.err.println("Erro ao fechar a sess�o "+ ex.getMessage());
			}
			
		}
	}
	
	/*
	 * M�todo excluir
	 */
	public void excluir(Contato contato){
		Session sessao = null;
		Transaction transacao = null;
		
		try{
			sessao = HibernateUtil.getSessionfactory().openSession();
			transacao = sessao.beginTransaction();
			
			sessao.delete(contato);		
			transacao.commit();
		}catch(HibernateException ex){
			System.err.println("Erro ao excluir o contato " + ex.getMessage());
		}finally{
			try{
				sessao.close();
			}catch(HibernateException ex){
				System.err.println("Erro ao fechar a sess�o "+ ex.getMessage());
			}
			
		}
	}
	
	/*
	 * M�todo Listar
	 */
	public List<Contato> listar(){
		Session sessao = null;
		Transaction transacao = null;
		Query consulta = null; //Query que gerar� o resultado da consulta
		List<Contato> resultado = null;
		
		try{
			sessao = HibernateUtil.getSessionfactory().openSession();
			transacao = sessao.beginTransaction();
			consulta = sessao.createQuery("from Contato"); //Query de consulta no banco. "Contato" � o nome da classe mapeada no xml
			resultado = consulta.list(); //Aqui, a lista de contados � recuperada do objeto query que j� executou a consulta
			transacao.commit();
			return resultado;
		}catch(HibernateException ex){
			System.err.println("Erro ao listar o contato " + ex.getMessage());
			return null;
		}finally{
			try{
				sessao.close();
			}catch(HibernateException ex){
				System.err.println("Erro ao fechar a sess�o "+ ex.getMessage());
			}
			
		}
	}
	
	/*
	 * M�todo buscar
	 */
	public Contato buscar(int valor){
		Session sessao = null;
		Transaction transacao = null;
		Contato contato = null;
		Query consulta = null;
		
		try{
			sessao = HibernateUtil.getSessionfactory().openSession();
			transacao = sessao.beginTransaction();
			consulta = sessao.createQuery("from Contato where codigo = :parametro"); //Query de busca em HQL			
			consulta.setInteger("parametro", valor); //Seta o atributo de criterio de pesquisa na query, semelhante ao PreparedStatement
			contato = (Contato) consulta.uniqueResult();			
			transacao.commit();
			return contato;			
		}catch(HibernateException ex){
			System.err.println("Erro ao buscar o contato " + ex.getMessage());
			return null;
		}finally{
			try{
				sessao.close();
			}catch(HibernateException ex){
				System.err.println("Erro ao fechar a sess�o "+ ex.getMessage());
			}
			
		}
	}
}
