package br.com.metriken.view.apf.documentos;

import javax.inject.Named;

import org.apache.myfaces.extensions.cdi.core.api.scope.conversation.ConversationScoped;

import br.com.metriken.model.nopersistence.apf.Documento;
import br.com.metriken.view.base.crud.AbstractAPF;

@Named
@ConversationScoped
public class DocumentosUtilizadosBean extends AbstractAPF {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5171874626381537874L;

	public DocumentosUtilizadosBean() {
		this.carregaListaDocumentosIniciais();
	}

	private void carregaListaDocumentosIniciais() {
		for (int i = 0; i < 5; i++) {
			this.getViewHelper().getDocumentosUtilizados().add(new Documento());
		}
	}

	public void adicionaLinhaAbaixo(int index) {
		this.getViewHelper().getDocumentosUtilizados().add(index + 1, new Documento());
	}

	public void removeLinhaSelecionada(int index) {
		if (this.getViewHelper().getDocumentosUtilizados().size() > 1) {
			this.getViewHelper().getDocumentosUtilizados().remove(index);
		}
	}

	public void adicionaLinhas() {
		int firstIndex = this.getViewHelper().getDocumentosUtilizados().size();
		for (int i = firstIndex; i < firstIndex + 4; i++) {
			this.getViewHelper().getDocumentosUtilizados().add(firstIndex, new Documento());
		}
	}
}
