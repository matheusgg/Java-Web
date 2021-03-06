package br.com.metriken.view.apf.metrica;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.myfaces.extensions.cdi.core.api.scope.conversation.ConversationScoped;

import br.com.metriken.facade.contagem.ContagemFacade;
import br.com.metriken.view.base.crud.APFViewHelper;
import br.com.metriken.view.base.crud.AbstractAPF;

@Named
@ConversationScoped
public class MetricaBean extends AbstractAPF {

	/**
	 * 
	 */
	private static final long serialVersionUID = -562720555300242615L;

	/**
	 * Decorator para os tipos de metrica.
	 */
	private MetricaDecorator decorator;

	@Inject
	private ContagemFacade comboFacade;

	@PostConstruct
	public void begin() {
		this.getViewHelper().setComboTipoMetrica(this.comboFacade.getComboTipoMetrica());
	}

	@Override
	public void begin(APFViewHelper viewHelper) {
		this.setViewHelper(viewHelper);

		if (this.getViewHelper().getProjeto().getTipoContagem().getCodigo() == 1) {
			this.decorator = new MetricaEstimada(this.getViewHelper());
		} else {
			this.decorator = new MetricaDetalhada(this.getViewHelper());
		}
		this.decorator.carregaLista();
	}

	/**
	 * @return the decorator
	 */
	public MetricaDecorator getDecorator() {
		return decorator;
	}

	/**
	 * @param decorator
	 *            the decorator to set
	 */
	public void setDecorator(MetricaDecorator decorator) {
		this.decorator = decorator;
	}

}
