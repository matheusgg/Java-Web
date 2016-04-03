package financeiro.entidade;

import java.util.List;

import financeiro.util.DAOFactory;

public class EntidadeRN {
	private EntidadeDAO entidadeDAO;

	public EntidadeRN() {
		this.entidadeDAO = DAOFactory.criarEntidadeDAO();
	}

	public void salvar(Entidade entidade) {
		this.entidadeDAO.salvar(entidade);
	}

	public List<Entidade> listar(String entidade) {
		return this.entidadeDAO.listar(entidade);
	}

	public Entidade buscar(String entidade) {
		return (Entidade) this.entidadeDAO.buscar(entidade);
	}

}
