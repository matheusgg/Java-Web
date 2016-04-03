package com.livro.capitulo3.media;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.livro.capitulo3.util.HibernateUtil;

public class MediaDAO {
	private Session sessao;
	private Transaction transacao;

	/**
	 * Método salvar
	 * @param media
	 */
	public void salvar(Media media) {
		try {
			this.sessao = HibernateUtil.getSessionfactory().openSession();
			this.transacao = sessao.beginTransaction();
			this.sessao.save(media);
			this.transacao.commit();
		} catch (HibernateException ex) {
			System.err.println("Erro ao salvar a media! " + ex.getMessage());
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
	public void atualizar(Media media){
		try{
			this.sessao = HibernateUtil.getSessionfactory().openSession();
			this.transacao = sessao.beginTransaction();
			this.sessao.update(media);
			this.transacao.commit();
		} catch (HibernateException ex) {
			System.err.println("Erro ao atualizar a media! " + ex.getMessage());
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
	public void excluir(Media media){
		try{
			this.sessao = HibernateUtil.getSessionfactory().openSession();
			this.transacao = sessao.beginTransaction();
			this.sessao.delete(media);
			this.transacao.commit();
		} catch (HibernateException ex) {
			System.err.println("Erro ao excluir a media! " + ex.getMessage());
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
	public Media buscarMedia(int codigo){
		Media media = null;
		try{
			this.sessao = HibernateUtil.getSessionfactory().openSession();
			this.transacao = sessao.beginTransaction();	
			Criteria filtro = this.sessao.createCriteria(Media.class);
			filtro.add(Restrictions.eq("media", codigo));
			media = (Media) filtro.uniqueResult();
			this.transacao.commit();
		} catch (HibernateException ex) {
			System.err.println("Erro ao buscar a media! " + ex.getMessage());
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
		return media;
	}
	
	/**
	 * Método Listar
	 */
	@SuppressWarnings("unchecked")
	public List<Media> listar(){
		List<Media> medias = null;
		try{
			this.sessao = HibernateUtil.getSessionfactory().openSession();
			this.transacao = sessao.beginTransaction();
			Criteria filtro = this.sessao.createCriteria(Media.class);
			medias = filtro.list();
			this.transacao.commit();
		}catch(HibernateException ex){
			System.err.println("Erro ao listar as medias! " + ex.getMessage());
		}finally{
			try{
				if(this.sessao.isOpen()){
					this.sessao.close();
				}
			}catch(HibernateException ex){
				System.err.println("Erro ao encerrar a sessão! " + ex.getMessage());
			}
		}
		return medias;
	}	
}
