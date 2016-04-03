package br.com.metriken.view.apf.metrica;

import java.util.ArrayList;

import br.com.metriken.model.nopersistence.apf.APFEstimada;
import br.com.metriken.view.base.crud.APFViewHelper;

public class MetricaEstimada implements MetricaDecorator {

	private APFViewHelper vh;

	public MetricaEstimada(APFViewHelper vh) {
		this.vh = vh;
	}

	@Override
	public void adicionaLinhaAbaixo(int index) {
		this.vh.getAPFEstimadas().add(index + 1, new APFEstimada());
	}

	@Override
	public void removeLinhaSelecionada(int index) {
		if (this.vh.getAPFEstimadas().size() > 1) {
			this.vh.getAPFEstimadas().remove(index);
		}
	}

	@Override
	public void adicionaLinhas() {
		int firstIndex = this.vh.getAPFEstimadas().size();
		for (int i = firstIndex; i < firstIndex + 5; i++) {
			this.vh.getAPFEstimadas().add(firstIndex, new APFEstimada());
		}
	}

	@Override
	public void carregaLista() {
		this.vh.setAPFEstimadas(new ArrayList<APFEstimada>());
		for (int i = 0; i < 5; i++) {
			this.vh.getAPFEstimadas().add(new APFEstimada());
		}
	}

	@Override
	public void gerarCRUD() {
		for (int index = 0; index < this.vh.getAPFEstimadas().size(); index++) {
			if (this.vh.getAPFEstimadas().get(index).isSelected() && this.vh.getAPFEstimadas().get(index).getElementoCrud().isEmpty()) {
				this.vh.getAPFEstimadas().get(index).setElementoCrud(new ArrayList<APFEstimada>());
				for (int i = 0; i < 5; i++) {
					this.vh.getAPFEstimadas().get(index).getElementoCrud().add(new APFEstimada());
				}
			}
		}
	}

}
