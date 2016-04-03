package br.com.metriken.facade.contagem;

import java.util.ArrayList;
import java.util.List;

import br.com.metriken.facade.base.AbstractFacade;
import br.com.metriken.model.dao.apf.contagem.ContagemDaoImpl;
import br.com.metriken.model.nopersistence.apf.Cfps;
import br.com.metriken.model.nopersistence.apf.TipoContagem;
import br.com.metriken.model.nopersistence.apf.TipoMetrica;
import br.com.metriken.model.nopersistence.apf.TipoProjeto;
import br.com.metriken.util.FacesUtils;

public class ContagemFacade extends AbstractFacade<ContagemDaoImpl> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4686274105636211951L;

	public ContagemFacade() {
		super(ContagemDaoImpl.class);
	}

	public List<TipoProjeto> getComboTipoProjeto() {
		return this.getDao().pesquisaTipoProjeto();
	}

	public List<TipoContagem> getComboTipoContagem() {
		return this.getDao().pesquisaTipoContagem();
	}

	public List<Cfps> getComboCFPS() {
		List<Cfps> itens = new ArrayList<>();
		itens.add(new Cfps(1, FacesUtils.getMessageByKey("lbl_sim")));
		itens.add(new Cfps(2, FacesUtils.getMessageByKey("lbl_nao")));
		return itens;
	}

	public List<TipoMetrica> getComboTipoMetrica() {
		return this.getDao().pesquisaTipoMetrica();
	}

}
