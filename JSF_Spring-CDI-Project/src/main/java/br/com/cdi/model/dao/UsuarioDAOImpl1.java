package br.com.cdi.model.dao;

import br.com.cdi.model.Usuario;
import br.com.cdi.qualificadores.Qualificador;

/**
 * Esta é uma anotação personalizada e é anotada com '@Qualifier'. Ela qualifica
 * um bean CDI, e diz que esta implementação deve ser utilizada nos pontos de
 * injeção que possuem esta mesma anotação.
 * 
 * @author Matheus
 * 
 */
@Qualificador
public class UsuarioDAOImpl1 implements UsuarioDAO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void salva(Usuario usuario) {
		System.out.println("Usuário salvo! IMPL 1");
	}

}
