package br.com.metriken.model.dao.apf.contagem;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;

import br.com.metriken.model.dao.base.AbstractGenericDAO;
import br.com.metriken.model.nopersistence.apf.TipoContagem;
import br.com.metriken.model.nopersistence.apf.TipoMetrica;
import br.com.metriken.model.nopersistence.apf.TipoProjeto;
import br.com.metriken.util.FacesUtils;

@Local
@Stateless
public class ContagemDaoImpl extends AbstractGenericDAO<Object> implements ContagemDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3629570528161497803L;

	@Override
	public List<TipoContagem> pesquisaTipoContagem() {
		List<TipoContagem> tipo = new ArrayList<TipoContagem>();
		tipo.add(new TipoContagem(1, FacesUtils.getMessageByKey("lbl_estimada")));
		tipo.add(new TipoContagem(2, FacesUtils.getMessageByKey("lbl_detalhada")));
		return tipo;
	}

	@Override
	public List<TipoProjeto> pesquisaTipoProjeto() {
		List<TipoProjeto> tipo = new ArrayList<TipoProjeto>();
		tipo.add(new TipoProjeto(1, FacesUtils.getMessageByKey("lbl_desenvolvimento")));
		tipo.add(new TipoProjeto(2, FacesUtils.getMessageByKey("lbl_melhoria")));
		return tipo;
	}

	@Override
	public List<TipoMetrica> pesquisaTipoMetrica() {
		List<TipoMetrica> tipo = new ArrayList<TipoMetrica>();
		tipo.add(new TipoMetrica(1, FacesUtils.getMessageByKey("lbl_ali")));
		tipo.add(new TipoMetrica(2, FacesUtils.getMessageByKey("lbl_aie")));
		tipo.add(new TipoMetrica(3, FacesUtils.getMessageByKey("lbl_ee")));
		tipo.add(new TipoMetrica(4, FacesUtils.getMessageByKey("lbl_ce")));
		tipo.add(new TipoMetrica(5, FacesUtils.getMessageByKey("lbl_se")));
		tipo.add(new TipoMetrica(6, FacesUtils.getMessageByKey("lbl_nm")));
		return tipo;
	}

}
