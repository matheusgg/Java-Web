package br.com.maestroautomacoes.portal.model.dao.orcamento;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;

import br.com.maestroautomacoes.portal.model.dao.util.AbstractDao;
import br.com.maestroautomacoes.portal.model.dao.util.Dao;
import br.com.maestroautomacoes.portal.model.orcamento.ItemOrcamento;
import br.com.maestroautomacoes.portal.model.orcamento.ItensDoOrcamento;
import br.com.maestroautomacoes.portal.model.orcamento.Orcamento;
import br.com.maestroautomacoes.portal.model.usuario.Usuario;

@Dao
public class OrcamentoDaoImpl extends AbstractDao<Orcamento> implements OrcamentoDao {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8197379894176864853L;

	@SuppressWarnings("unchecked")
	@Override
	public List<ItemOrcamento> buscaListadeItens(int codigo) {
		List<ItemOrcamento> itens = new ArrayList<ItemOrcamento>();
		Query query = this.sessao.createQuery("select item from ItemOrcamento item where item.tipoItem.idTipoItem = :tipoItem");
		query.setParameter("tipoItem", codigo);
		itens = query.list();
		return itens;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Orcamento> buscaOrcamentosDoCliente(Usuario usuario) {
		List<Orcamento> orcamentos = new ArrayList<Orcamento>();
		Query query = this.sessao.createQuery("select orc from Orcamento orc where orc.usuario.idUsuario = :idUsuario");
		query.setParameter("idUsuario", usuario.getIdUsuario());
		orcamentos = query.list();
		for (Orcamento orcamento : orcamentos) {
			Hibernate.initialize(orcamento.getItensDoOrcamento());
		}
		return orcamentos;
	}

	@Override
	public void cadastrarNovoOrcamento(Orcamento orcamento) {
		super.salva(orcamento);
		for (ItensDoOrcamento item : orcamento.getItensDoOrcamento()) {
			item.getId().setIdOrcamento(orcamento.getIdOrcamento());
		}
	}
}
