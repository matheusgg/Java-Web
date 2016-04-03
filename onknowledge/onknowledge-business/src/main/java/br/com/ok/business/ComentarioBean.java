package br.com.ok.business;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import br.com.ok.model.Comentario;
import br.com.ok.model.dao.ComentarioDAO;

/**
 * The Class ComentarioBean.
 *
 * @author Matheus
 * @version 1.0 - 11/10/2014
 */
@Stateless
public class ComentarioBean implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -2113452905776314027L;

	/** The comentario dao. */
	@Inject
	private ComentarioDAO comentarioDAO;

	/** The usuario bean. */
	@Inject
	private UsuarioBean usuarioBean;

	/**
	 * Pesquisa comentarios por questao.
	 *
	 * @param idQuestao
	 *            the id questao
	 * @return the list
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<Comentario> pesquisaComentariosPorQuestao(Integer idQuestao) {
		List<Comentario> comentarios = this.comentarioDAO.findByQuestao(idQuestao);
		comentarios.forEach(comentario -> this.usuarioBean.preparaFotoPerfilUsuario(comentario.getUsuario()));
		return comentarios;
	}

	/**
	 * Pesquisa comentarios por atividade.
	 *
	 * @param idAtividade
	 *            the id atividade
	 * @return the list
	 */
	@TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
	public List<Comentario> pesquisaComentariosPorAtividade(Integer idAtividade) {
		List<Comentario> comentarios = this.comentarioDAO.findByAtividade(idAtividade);
		comentarios.forEach(comentario -> this.usuarioBean.preparaFotoPerfilUsuario(comentario.getUsuario()));
		return comentarios;
	}

	/**
	 * Salva comentario.
	 *
	 * @param comentario
	 *            the comentario
	 */
	public void salvaComentario(Comentario comentario) {
		this.comentarioDAO.save(comentario);
	}

	/**
	 * Removes the comentario.
	 *
	 * @param comentario
	 *            the comentario
	 */
	public void removeComentario(Comentario comentario) {
		this.comentarioDAO.remove(this.comentarioDAO.update(comentario));
	}

}
