package br.com.ok.view;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;

import lombok.Getter;
import lombok.Setter;

import org.apache.commons.lang3.StringUtils;
import org.omnifaces.util.Faces;

import br.com.ok.facade.ComentarioFacade;
import br.com.ok.model.Atividade;
import br.com.ok.model.Comentario;
import br.com.ok.model.Questao;
import br.com.ok.util.constants.OKConstants;
import br.com.ok.view.base.OKBaseMB;
import br.com.ok.view.base.annotation.OnKnowledgeMB;

/**
 * The Class ComentarioMB.
 *
 * @author Matheus
 * @version 1.0 - 11/10/2014
 */
@OnKnowledgeMB
public class ComentarioMB extends OKBaseMB {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 2238585408557676749L;

	/** The comentario facade. */
	@Inject
	private ComentarioFacade comentarioFacade;

	/** The comentarios. */
	@Getter
	@Setter
	private List<Comentario> comentarios;

	/** The comentario. */
	@Getter
	@Setter
	private Comentario comentario;

	/** The questao selecionada. */
	@Setter
	private Questao questaoSelecionada;

	/** The atividade selecionada. */
	@Setter
	private Atividade atividadeSelecionada;

	/**
	 * Prepara comentarios questao.
	 *
	 * @param questao
	 *            the questao
	 */
	public void preparaComentariosQuestao(Questao questao) {
		this.comentarios = new LinkedList<>(this.comentarioFacade.pesquisaComentariosPorQuestao(questao.getId()));
		this.questaoSelecionada = questao;
		this.preparaNovoComentario();
		super.getRequestContext().execute("$('#comentariosModal').modal('show')");
	}

	/**
	 * Prepara comentarios atividade.
	 *
	 * @param atividade
	 *            the atividade
	 */
	public void preparaComentariosAtividade(Atividade atividade) {
		this.comentarios = new LinkedList<>(this.comentarioFacade.pesquisaComentariosPorAtividade(atividade.getId()));
		this.atividadeSelecionada = atividade;
		this.preparaNovoComentario();
		super.getRequestContext().execute("$('#comentariosModal').modal('show')");
	}

	/**
	 * Adiciona comentario.
	 */
	public void adicionaComentario() {
		if (StringUtils.isNotBlank(this.comentario.getDescricao())) {
			this.comentario.setDataEnvio(new Date());
			this.comentarioFacade.salvaComentario(this.comentario);
			this.comentarios.add(OKConstants.VALOR_ZERO, this.comentario);
			this.preparaNovoComentario();
		}
	}

	/**
	 * Removes the comentario.
	 *
	 * @param index
	 *            the index
	 */
	public void removeComentario(int index) {
		Comentario comentario = this.comentarios.remove(index);
		this.comentarioFacade.removeComentario(comentario);
	}

	/**
	 * Prepara novo comentario.
	 */
	private void preparaNovoComentario() {
		this.comentario = new Comentario();
		this.comentario.setQuestao(this.questaoSelecionada);
		this.comentario.setAtividade(this.atividadeSelecionada);
		this.comentario.setUsuario(super.getSecurityContext().getLoggedUser());
	}

	/**
	 * Visualiza perfil usuario selecionado.
	 *
	 * @param id
	 *            the id
	 * @return the string
	 */
	public String visualizaPerfilUsuarioSelecionado(Integer id) {
		Faces.getFlash().put(OKConstants.USER_ID_KEY, id);
		return OKConstants.PRETTY_VIEW_PROFILE;
	}

}
