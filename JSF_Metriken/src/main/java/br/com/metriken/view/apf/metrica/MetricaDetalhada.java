package br.com.metriken.view.apf.metrica;

import java.util.ArrayList;

import br.com.metriken.model.nopersistence.apf.APFDetalhada;
import br.com.metriken.view.base.crud.APFViewHelper;

public class MetricaDetalhada implements MetricaDecorator {

	private APFViewHelper vh;

	public MetricaDetalhada(APFViewHelper vh) {
		this.vh = vh;
	}

	@Override
	public void adicionaLinhaAbaixo(int index) {
		this.vh.getAPFDetalhadas().add(index + 1, new APFDetalhada());
	}

	@Override
	public void removeLinhaSelecionada(int index) {
		if (this.vh.getAPFDetalhadas().size() > 1) {
			this.vh.getAPFDetalhadas().remove(index);
		}
	}

	@Override
	public void adicionaLinhas() {
		int firstIndex = this.vh.getAPFDetalhadas().size();
		for (int i = firstIndex; i < firstIndex + 5; i++) {
			this.vh.getAPFDetalhadas().add(firstIndex, new APFDetalhada());
		}
	}

	@Override
	public void carregaLista() {
		this.vh.setAPFDetalhadas(new ArrayList<APFDetalhada>());
		for (int i = 0; i < 5; i++) {
			this.vh.getAPFDetalhadas().add(new APFDetalhada());
		}
	}

	@Override
	public void gerarCRUD() {
		for (int index = 0; index < this.vh.getAPFDetalhadas().size(); index++) {
			if (this.vh.getAPFDetalhadas().get(index).isSelected() && this.vh.getAPFDetalhadas().get(index).getElementoCrud().isEmpty()) {
				this.vh.getAPFDetalhadas().get(index).setElementoCrud(new ArrayList<APFDetalhada>());
				for (int i = 0; i < 5; i++) {
					this.vh.getAPFDetalhadas().get(index).getElementoCrud().add(new APFDetalhada());
				}
			}
		}
	}

}
