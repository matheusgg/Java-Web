package br.com.maestroautomacoes.portal.model.dao.orcamento;

import java.util.List;

import br.com.maestroautomacoes.portal.model.orcamento.ItemOrcamento;
import br.com.maestroautomacoes.portal.model.orcamento.Orcamento;
import br.com.maestroautomacoes.portal.model.usuario.Usuario;

public interface OrcamentoDao {

	List<ItemOrcamento> buscaListadeItens(int codigo);

	List<Orcamento> buscaOrcamentosDoCliente(Usuario usuario);

	void cadastrarNovoOrcamento(Orcamento orcamento);

}
