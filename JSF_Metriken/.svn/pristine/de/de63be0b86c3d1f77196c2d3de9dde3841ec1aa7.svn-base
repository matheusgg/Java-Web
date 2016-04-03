package br.com.metriken.view.apf.premissas;

import javax.inject.Named;

import org.apache.myfaces.extensions.cdi.core.api.scope.conversation.ConversationScoped;

import br.com.metriken.model.nopersistence.apf.Premissa;
import br.com.metriken.view.base.crud.AbstractAPF;

@Named
@ConversationScoped
public class PremissasBean extends AbstractAPF {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5978547705371107163L;

	public PremissasBean() {
		this.carregaListaPremissasIniciais();
	}

	private void carregaListaPremissasIniciais() {
		for (int i = 0; i < 5; i++) {
			this.getViewHelper().getPremissas().add(new Premissa());
		}
	}

	public void adicionaLinhaAbaixo(int index) {
		this.getViewHelper().getPremissas().add(index + 1, new Premissa());
	}

	public void removeLinhaSelecionada(int index) {
		if (this.getViewHelper().getPremissas().size() > 1) {
			this.getViewHelper().getPremissas().remove(index);
		}
	}

	public void adicionaLinhas() {
		int firstIndex = this.getViewHelper().getPremissas().size();
		for (int i = firstIndex; i < firstIndex + 4; i++) {
			this.getViewHelper().getPremissas().add(firstIndex, new Premissa());
		}
	}

}
