package br.com.metriken.view.apf.tabcontroller;

import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.TabChangeEvent;

import br.com.metriken.view.apf.dadosgerais.DadosGeraisBean;
import br.com.metriken.view.apf.metrica.MetricaBean;

@Named("apfTabControlBean")
public class APFTabControlBean {

	@Inject
	private DadosGeraisBean dadosGeraisBean;

	@Inject
	private MetricaBean metricaBean;

	/**
	 * Controle de mudanca de tab
	 */
	public void tabControl(TabChangeEvent event) {
		String tab = event.getTab().getId();
		if (tab.equals("tabMetrica")) {
			this.metricaBean.begin(this.dadosGeraisBean.getViewHelper());
		}
	}

}
