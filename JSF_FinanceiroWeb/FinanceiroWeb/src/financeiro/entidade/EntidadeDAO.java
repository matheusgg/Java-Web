package financeiro.entidade;

import java.util.List;

public interface EntidadeDAO {

	public void salvar(Entidade entidade);

	public List<Entidade> listar(String entidade);

	public Entidade buscar(String entidade);

}
