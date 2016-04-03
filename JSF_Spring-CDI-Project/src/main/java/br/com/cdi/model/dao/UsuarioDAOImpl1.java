package br.com.cdi.model.dao;

import br.com.cdi.model.Usuario;
import br.com.cdi.qualificadores.Qualificador;

/**
 * Esta � uma anota��o personalizada e � anotada com '@Qualifier'. Ela qualifica
 * um bean CDI, e diz que esta implementa��o deve ser utilizada nos pontos de
 * inje��o que possuem esta mesma anota��o.
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
		System.out.println("Usu�rio salvo! IMPL 1");
	}

}
